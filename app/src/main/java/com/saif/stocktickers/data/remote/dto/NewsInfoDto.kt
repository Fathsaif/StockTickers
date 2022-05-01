package com.saif.stocktickers.data.remote.dto

import com.squareup.moshi.Json

data class NewsInfoDto(
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "urlToImage") val image: String?,
    @field:Json(name = "publishedAt") val date: String?,
)
