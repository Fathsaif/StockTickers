package com.saif.stocktickers.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsListings(
        stockListingsEntities: List<NewsInfoEntity>?
    )

    @Query("DELETE FROM newsinfoentity")
    suspend fun clearNewsListings()

    @Query(
        """
            SELECT * 
            FROM newsinfoentity
           
        """
    )
    suspend fun getNewsListings(): List<NewsInfoEntity>
}