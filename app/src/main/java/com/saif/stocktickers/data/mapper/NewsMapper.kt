package com.saif.stocktickers.data.mapper

import com.saif.stocktickers.data.local.NewsInfoEntity
import com.saif.stocktickers.data.remote.dto.NewsInfoDto
import com.saif.stocktickers.domain.model.NewsInfo
import com.saif.stocktickers.util.dateToString
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun NewsInfoEntity.toNewsInfo(): NewsInfo {
    val pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(date, formatter)
    return NewsInfo(
        title = title ?: "",
        description = description ?: "",
        image = image ?: "",
        date = localDateTime.dateToString("E dd MMM hh:mm a")
    )
}
fun NewsInfoDto.toNewsInfoEntity(): NewsInfoEntity {

    return NewsInfoEntity(
        title = title ?: "",
        description = description ?: "",
        image = image ?: "",
        date = date
    )
}