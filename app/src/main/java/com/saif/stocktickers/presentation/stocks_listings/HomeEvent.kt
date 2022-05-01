package com.saif.stocktickers.presentation.stocks_listings

sealed class HomeEvent {
    object Refresh: HomeEvent()
    data class OnSearchQueryChange(val query: String): HomeEvent()
}
