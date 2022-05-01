package com.saif.stocktickers.presentation.stocks_listings

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun HomeListingsScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollState()
            )
    ) {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text("Stock Tickers")
            },
                    backgroundColor = MaterialTheme.colors.primarySurface,

            )
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(HomeEvent.Refresh)
            }
        ) {
            if (state.isLoading) Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
            else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Text(
                            text = "Stocks Tickers",
                            color = androidx.compose.ui.graphics.Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                if (state.isStocksLoading) {
                   item {  Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }}
                } else {
                    item {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {

                            items(state.stocks.size) { i ->
                                val company = state.stocks[i]
                                StockItem(
                                    stock = company,
                                )
                            }
                        }
                    }
                }
                    item {
                        Text(
                            text = "Featured News",
                            color = androidx.compose.ui.graphics.Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    item {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {

                            items(state.firstNews.size) { i ->
                                val news = state.firstNews[i]
                                FirstNewsItem(
                                    news = news,

                                    )
                            }
                        }
                    }
                    item {
                        Text(
                            text = "News",
                            color = androidx.compose.ui.graphics.Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    items(state.newsList.size) { i ->
                        val news = state.newsList[i]
                        NewsItem(
                            news = news,

                            )
                    }

                }
            }
        }
    }
}