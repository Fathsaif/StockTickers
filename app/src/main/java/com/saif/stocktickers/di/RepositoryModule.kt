package com.saif.stocktickers.di

import com.saif.stocktickers.data.csv.CSVParser
import com.saif.stocktickers.data.csv.StocksListingsParser
import com.saif.stocktickers.data.repository.StockRepositoryImpl
import com.saif.stocktickers.domain.model.StockListing
import com.saif.stocktickers.domain.model.NewsInfo
import com.saif.stocktickers.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        stocksListingsParser: StocksListingsParser
    ): CSVParser<StockListing>


    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}