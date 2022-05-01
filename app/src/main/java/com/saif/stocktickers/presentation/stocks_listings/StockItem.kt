package com.saif.stocktickers.presentation.stocks_listings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saif.stocktickers.domain.model.StockListing


@Composable
fun StockItem(
    stock: StockListing,
) {


            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Black)
                    .height(80.dp)
                    .width(80.dp)
                    .wrapContentSize(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stock.symbol,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f).padding(4.dp)

                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stock.price,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colors.onBackground
                )
            }



}