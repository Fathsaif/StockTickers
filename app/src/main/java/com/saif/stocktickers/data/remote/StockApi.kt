package com.saif.stocktickers.data.remote

import com.saif.stocktickers.data.remote.dto.NewsListDto
import okhttp3.ResponseBody
import retrofit2.http.GET

interface StockApi {

    @GET("https://raw.githubusercontent.com/dsancov/TestData/main/stocks.csv")
    suspend fun getListings(
    ): ResponseBody

    @GET("NewsAPI/everything/cnn.json")
    suspend fun getNews(
    ): NewsListDto


    companion object {
        const val BASE_URL = "https://saurav.tech/"
    }
}