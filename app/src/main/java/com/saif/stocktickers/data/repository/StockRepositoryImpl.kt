package com.saif.stocktickers.data.repository

import com.saif.stocktickers.data.csv.CSVParser
import com.saif.stocktickers.data.local.StockDatabase
import com.saif.stocktickers.data.mapper.toNewsInfo
import com.saif.stocktickers.data.mapper.toNewsInfoEntity
import com.saif.stocktickers.data.remote.StockApi
import com.saif.stocktickers.domain.model.NewsInfo
import com.saif.stocktickers.domain.model.StockListing
import com.saif.stocktickers.domain.repository.StockRepository
import com.saif.stocktickers.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    db: StockDatabase,
    private val stockListingsParser: CSVParser<StockListing>,
): StockRepository {
    private val dao = db.NewsDao

    override suspend fun getStocksListings(
        
    ): Flow<Resource<List<StockListing>>> {
        return flow {
            emit(Resource.Loading(true))
           
            val remoteListings = try {
                val response = api.getListings()
                stockListingsParser.parse(response.byteStream())
            } catch(e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            emit(Resource.Success(
                remoteListings
            ))
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getNewsListings(fetchFromRemote: Boolean):Flow< Resource<List<NewsInfo>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.getNewsListings()
            emit(Resource.Success(
                data = localListings.map {
                    it.toNewsInfo()
                }
            ))

            val shouldJustLoadFromCache = localListings.isNotEmpty() && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                api.getNews()
            } catch(e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.articles?.let { listings ->
                dao.clearNewsListings()
                dao.insertNewsListings(
                    listings?.map { it.toNewsInfoEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .getNewsListings()
                        .map { it.toNewsInfo() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }


}