package com.smile.mypark.presentation.home

import androidx.lifecycle.viewModelScope
import com.smile.mypark.core.base.BaseViewModel
import com.smile.mypark.data.mapper.toUi
import com.smile.mypark.domain.usecase.ShopLikeUseCase
import com.smile.mypark.domain.usecase.ShopSearchUseCase
import com.smile.mypark.domain.usecase.ShopUnlikeUseCase
import com.smile.mypark.presentation.home.component.StoreUi
import kotlinx.coroutines.launch

class MapViewModel(
    private val searchShops: ShopSearchUseCase,
    private val likeShop: ShopLikeUseCase,
    private val unlikeShop: ShopUnlikeUseCase
) : BaseViewModel<MapContract.State, MapContract.SideEffect, MapContract.Event>(
    MapContract.State()
) {

    override fun handleEvents(event: MapContract.Event) {
        when (event) {
            is MapContract.Event.KeywordChanged -> updateState { copy(keyword = event.value) }

            is MapContract.Event.ClickSearch -> onSearch(event.keyword)

            MapContract.Event.MapReady -> onMapReady()

            is MapContract.Event.ClickStore -> onClickStore(event.store)

            MapContract.Event.ClickBack -> sendEffect ({ MapContract.SideEffect.NavigateBack })

            MapContract.Event.ClickMenu -> sendEffect ({ MapContract.SideEffect.OpenMenu })

            MapContract.Event.ClickOpenList -> sendEffect( { MapContract.SideEffect.OpenStoreList })

            MapContract.Event.ClickMyLocation -> onClickMyLocation()

            is MapContract.Event.ClickCall -> onClickCall(event.store)

            is MapContract.Event.ClickRoute -> onClickRoute(event.store)
            MapContract.Event.ClickToggleLike -> toggleLike()
        }
    }

    private fun onMapReady() {
        updateState { copy(isMapReady = true) }
    }

    private fun onSearch(rawKeyword: String) {
        val keyword = rawKeyword.trim()
        if (keyword.isBlank()) {
            sendEffect ({ MapContract.SideEffect.Toast("검색어를 입력해 주세요.") })
            return
        }

        viewModelScope.launch {
            updateState { copy(isLoading = true, error = null) }

            runCatching {
                searchShops(keyword)
            }.onSuccess { result ->
                val uiList = result.map { it.toUi() }
                updateState {
                    copy(
                        isLoading = false,
                        stores = uiList,
                        selected = uiList.firstOrNull(),
                        error = null
                    )
                }

                // 첫 매장으로 카메라 이동
                uiList.firstOrNull()?.let { first ->
                    sendEffect ({
                        MapContract.SideEffect.MoveCamera(
                            lat = first.lat,
                            lng = first.lng
                        )
                    })
                }
            }.onFailure { t ->
                updateState { copy(isLoading = false, error = t.message) }
                sendEffect ({
                    MapContract.SideEffect.Toast(
                        t.message ?: "매장 검색에 실패했어요."
                    )
                })
            }
        }
    }

    private fun onClickStore(store: StoreUi) {
        updateState { copy(selected = store) }
        sendEffect ({
            MapContract.SideEffect.MoveCamera(
                lat = store.lat,
                lng = store.lng
            )
        })
    }

    private fun onClickMyLocation() {

        sendEffect ({
            MapContract.SideEffect.Toast("내 위치 찾기는 아직 준비 중이에요.")
        })
    }

    private fun onClickCall(store: StoreUi) {
        val phone = store.phone.trim()
        if (phone.isBlank()) {
            sendEffect ({ MapContract.SideEffect.Toast("전화번호 정보가 없어요.") })
            return
        }
        sendEffect ({ MapContract.SideEffect.CallTo(phone) })
    }

    private fun onClickRoute(store: StoreUi) {
        if (store.lat == 0.0 && store.lng == 0.0) {
            sendEffect ({ MapContract.SideEffect.Toast("위치 정보가 없어요.") })
            return
        }
        sendEffect ({
            MapContract.SideEffect.OpenRoute(
                lat = store.lat,
                lng = store.lng,
                name = store.name
            )
        })
    }

    private fun toggleLike() {
        val s = viewState.value
        val current = s.selected

        if (current == null) {
            sendEffect ({ MapContract.SideEffect.Toast("선택된 매장이 없어요.") })
            return
        }

        val prevLiked = current.isLiked
        val newLiked = !prevLiked

        val updatedStores = s.stores.map { store ->
            if (store.shopCode == current.shopCode) store.copy(isLiked = newLiked) else store
        }
        val updatedSelected = current.copy(isLiked = newLiked)

        updateState {
            copy(
                stores = updatedStores,
                selected = updatedSelected
            )
        }

        viewModelScope.launch {
            val result = runCatching {
                if (newLiked) {
                    likeShop(current.shopCode)     // POST /like
                } else {
                    unlikeShop(current.shopCode)   // DELETE /like
                }
            }

            result.onFailure { t ->
                val rollbackStores = s.stores.map { store ->
                    if (store.shopCode == current.shopCode) store.copy(isLiked = prevLiked) else store
                }
                val rollbackSelected = current.copy(isLiked = prevLiked)

                updateState {
                    copy(
                        stores = rollbackStores,
                        selected = rollbackSelected
                    )
                }

                sendEffect ({ MapContract.SideEffect.Toast(t.message ?: "찜 상태 변경에 실패했어요.") })
            }
        }
    }

}
