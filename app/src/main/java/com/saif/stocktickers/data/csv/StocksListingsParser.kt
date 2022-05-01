package com.saif.stocktickers.data.csv

import com.opencsv.CSVReader
import com.saif.stocktickers.domain.model.StockListing
import com.saif.stocktickers.util.round
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StocksListingsParser @Inject constructor(): CSVParser<StockListing> {

    override suspend fun parse(stream: InputStream): List<StockListing> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val symbol = line.getOrNull(0)
                    val price = line.getOrNull(1)
                    StockListing(
                        symbol = symbol ?: return@mapNotNull null,
                        price = price?.toDouble()?.round().toString()
                    )
                }
                .also {
                    csvReader.close()
                }
        }
    }
}