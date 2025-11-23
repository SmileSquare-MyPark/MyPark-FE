package com.smile.mypark.presentation.home

import androidx.lifecycle.viewModelScope
import com.smile.mypark.core.base.BaseViewModel
import com.smile.mypark.domain.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository,
) : BaseViewModel<HomeContract.HomeViewState, HomeContract.HomeSidEffect, HomeContract.HomeEvent>(
    HomeContract.HomeViewState()
) {
    init {
        getHome()
    }

    override fun handleEvents(event: HomeContract.HomeEvent) {
        when (event) {
            else -> {}
        }
    }

    private fun getHome() = viewModelScope.launch {

        runCatching { homeRepository.getHome() }
            .onSuccess { result ->
                println("[Login] success i${result}")
                updateState { copy(homeInfo = result) }
            }
            .onFailure { t ->
                sendEffect({ HomeContract.HomeSidEffect.Toast(t.message ?: "error") })
            }
    }
}