package com.saif.stocktickers.domain.repository

import com.saif.stocktickers.domain.model.StockListing
import com.saif.stocktickers.domain.model.NewsInfo
import com.saif.stocktickers.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getStocksListings(

    ): Flow<Resource<List<StockListing>>>

    suspend fun getNewsListings(
        fetchFromRemote: Boolean

    ): Flow<Resource<List<NewsInfo>>>


}