package com.saif.stocktickers.presentation.stocks_listings

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.saif.stocktickers.domain.model.NewsInfo

@Composable
fun FirstNewsItem(
    news: NewsInfo,
) {
Log.d("infoNews",news.title.toString())
            Column(
                modifier = Modifier.padding(8.dp)
                    .background(Color.DarkGray).height(180.dp).width(200.dp)
                    .wrapContentSize(Alignment.Center)

            ) {
                Text(
                    text = news.title?:"",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().padding(8.dp).align(Alignment.CenterHorizontally),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    color = MaterialTheme.colors.onBackground
                )
                Spacer(modifier = Modifier.width(4.dp))

                Image(
                    painter = rememberAsyncImagePainter(news.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )

                Spacer(modifier = Modifier.width(8.dp))


            }



}