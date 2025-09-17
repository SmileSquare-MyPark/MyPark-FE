package com.smile.mypark.presentation.home

import com.smile.mypark.core.base.BaseViewModel

class HomeViewModel (

) : BaseViewModel<HomeContract.HomeViewState, HomeContract.HomeSidEffect, HomeContract.HomeEvent>(
    HomeContract.HomeViewState()
) {
    override fun handleEvents(event: HomeContract.HomeEvent) {
        when(event){
            else -> {}
        }
    }
}