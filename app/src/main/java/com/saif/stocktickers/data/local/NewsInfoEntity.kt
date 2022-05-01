package com.saif.stocktickers.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class NewsInfoEntity(
    @PrimaryKey val id: Int? = null,

    val description: String?,
    val title: String?,
    val image: String?,
    val date: String?
)
