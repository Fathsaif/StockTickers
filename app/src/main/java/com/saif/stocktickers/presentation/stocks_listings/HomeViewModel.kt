package com.saif.stocktickers.presentation.stocks_listings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saif.stocktickers.domain.repository.StockRepository
import com.saif.stocktickers.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {

    var state by mutableStateOf(StocksListingsState())

    private var searchJob: Job? = null

    init {
        getStocksListings()
        getNewsListings()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Refresh -> {
                getStocksListings()
                getNewsListings(true)
            }
        }
    }

    private fun getStocksListings(

    ) {
        viewModelScope.launch {
            repository
                .getStocksListings()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    stocks = listings
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isStocksLoading = result.isLoading)
                        }
                    }
                }
        }
    }

    private fun getNewsListings(
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getNewsListings(fetchFromRemote)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->

                                state = state.copy(
                                    firstNews = listings.take(6)
                                )
                                state = state.copy(
                                    newsList = listings.drop(6)
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}