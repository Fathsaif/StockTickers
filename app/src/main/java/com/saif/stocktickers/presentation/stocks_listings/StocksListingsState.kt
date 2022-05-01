package com.saif.stocktickers.presentation.stocks_listings

import com.saif.stocktickers.domain.model.NewsInfo
import com.saif.stocktickers.domain.model.StockListing

data class StocksListingsState(
    val stocks: List<StockListing> = emptyList(),
    val firstNews: List<NewsInfo> = emptyList(),
    val newsList: List<NewsInfo> = emptyList(),
    val isLoading: Boolean = false,
    val isStocksLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
