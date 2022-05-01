package com.saif.stocktickers.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListings(
        stockListingsEntities: List<StockListingsEntity>
    )

    @Query("DELETE FROM stocklistingsentity")
    suspend fun clearCompanyListings()

    @Query(
        """
            SELECT * 
            FROM stocklistingsentity
           
        """
    )
    suspend fun getStockListings(): List<StockListingsEntity>
}