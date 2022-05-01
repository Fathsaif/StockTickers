package com.saif.stocktickers.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StockListingsEntity(
    val name: String,
    val symbol: String,
    val price: String,
    @PrimaryKey val id: Int? = null
)
