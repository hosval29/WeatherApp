package com.hosvalandroiddev.weather_presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hosvalandroiddev.core.util.Resource
import com.hosvalandroiddev.core.util.UiEvent
import com.hosvalandroiddev.core.util.UiText
import com.hosvalandroiddev.weather_domain.use_case.WeatherUseCase
import com.hosvalandroiddev.core.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase
) : ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.OnQueryChange -> {
                state = state.copy(
                    query = event.query
                )
            }
            is SearchEvent.OnSearch -> {
                executeSearch()
            }
            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }
        }
    }

    private fun executeSearch() {
        viewModelScope.launch {
            weatherUseCase
                .getLocationByCoordinates(state.query)
                .onEach { resource ->
                    when(resource) {
                        is Resource.Success -> {
                            state = state.copy(
                                locations = resource.data ?: emptyList()
                            )
                        }
                        is Resource.Error -> {
                            _uiEvent.send(
                                UiEvent.ShowSnackbar(
                                    message = UiText.StringResource(R.string.error_something_went_wrong)
                                )
                            )
                        }
                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = resource.loading!!
                            )
                        }
                    }
                }
                .launchIn(this)
        }
    }

}