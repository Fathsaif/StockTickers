package com.saif.stocktickers.presentation.stocks_listings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.saif.stocktickers.domain.model.NewsInfo

@Composable
fun NewsItem(
    news: NewsInfo,
) {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .background(Color.DarkGray)
            .fillMaxSize()

    ) {
        Image(
            painter = rememberAsyncImagePainter(news.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(100.dp).height(100.dp).align(Alignment.CenterVertically).padding(4.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()

        ) {
            Text(
                text = news.title ?: "",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = MaterialTheme.colors.onBackground
            )

            Text(
                text = news.description ?: "",
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onBackground
            )

            Text(
                text = news.date,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(2.dp).fillMaxWidth(),
                color = MaterialTheme.colors.onBackground,
            fontSize = 12.sp,
                textAlign = TextAlign.End

            )

        }


    }


}