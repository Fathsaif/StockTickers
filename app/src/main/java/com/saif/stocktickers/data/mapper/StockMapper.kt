package com.saif.stocktickers.data.mapper

import com.saif.stocktickers.data.local.StockListingsEntity
import com.saif.stocktickers.domain.model.StockListing

fun StockListingsEntity.toCompanyListing(): StockListing {
    return StockListing(
        symbol = symbol,
        price = price
    )
}

fun StockListing.toCompanyListingEntity(): StockListingsEntity {
    return StockListingsEntity(
        name = "name",
        symbol = symbol,
        price = price
    )
}

