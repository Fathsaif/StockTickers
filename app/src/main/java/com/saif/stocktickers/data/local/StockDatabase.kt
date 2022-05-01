package com.saif.stocktickers.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [StockListingsEntity::class,NewsInfoEntity::class],
    version = 2
)
abstract class StockDatabase: RoomDatabase() {
    abstract val dao: StockDao
    abstract val NewsDao: NewsDao
}