package com.smile.mypark.presentation.home

import com.smile.mypark.data.mapper.toUi
import com.smile.mypark.domain.model.ShopResult
import com.smile.mypark.domain.usecase.ShopLikeUseCase
import com.smile.mypark.domain.usecase.ShopSearchUseCase
import com.smile.mypark.domain.usecase.ShopUnlikeUseCase
import com.smile.mypark.presentation.home.component.StoreUi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

object MapIosApi {

    private lateinit var searchUseCase: ShopSearchUseCase
    private lateinit var likeUseCase: ShopLikeUseCase
    private lateinit var unlikeUseCase: ShopUnlikeUseCase

    private val scope = MainScope()

    fun init(
        search: ShopSearchUseCase,
        like: ShopLikeUseCase,
        unlike: ShopUnlikeUseCase
    ) {
        searchUseCase = search
        likeUseCase = like
        unlikeUseCase = unlike
    }

    fun searchStores(
        keyword: String?,
        onResult: (List<StoreUi>?, Throwable?) -> Unit
    ) {
        checkInitialized()
        scope.launch {
            runCatching {
                val results: List<ShopResult> = searchUseCase(keyword)
                results.map { it.toUi() }
            }.onSuccess { list ->
                onResult(list, null)
            }.onFailure { t ->
                onResult(null, t)
            }
        }
    }

    fun toggleLike(
        shopCode: String,
        like: Boolean,
        onDone: (Throwable?) -> Unit
    ) {
        checkInitialized()
        scope.launch {
            runCatching {
                if (like) likeUseCase(shopCode) else unlikeUseCase(shopCode)
            }.onSuccess {
                onDone(null)
            }.onFailure { t ->
                onDone(t)
            }
        }
    }

    private fun checkInitialized() {
        check(::searchUseCase.isInitialized && ::likeUseCase.isInitialized && ::unlikeUseCase.isInitialized) {
            "MapIosApi.init(...) 이 먼저 호출돼야 합니다."
        }
    }
}