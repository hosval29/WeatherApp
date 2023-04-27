package com.hosvalandroiddev.weather_presentation.screens.map

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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase
) : ViewModel() {

    var state by mutableStateOf(MapState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: MapEvent) {
        when(event) {
            is MapEvent.OnSetLocationCity -> {
                state = state.copy(latLng = event.latLng)
                getTimeAnWeatherInfoByLocation(
                    lat = event.latLng.latitude,
                    lng = event.latLng.longitude
                )
            }
        }
    }

    private fun getTimeAnWeatherInfoByLocation(lat: Double, lng: Double) {
        viewModelScope.launch {
            val timeResult = weatherUseCase.getTimeByLocation(latitude = lat, longitude = lng)
            val weatherResult = weatherUseCase.getWeatherByLocation(latitude = lat, longitude = lng)

            timeResult.zip(weatherResult) { timeResource, weatherResource ->
                Pair(timeResource, weatherResource)
            }
                .collectLatest { (timeResource, weatherResource) ->
                    when {
                        timeResource is Resource.Success && weatherResource is Resource.Success -> {
                            state = state.copy(
                                isLoading = false,
                                locationWeather = weatherResource.data!!,
                                timeStamp = timeResource.data!!.formatted
                            )
                            _uiEvent.send(UiEvent.Navigate)
                        }
                        timeResource is Resource.Error -> {
                            _uiEvent.send(
                                UiEvent.ShowSnackbar(
                                    message = UiText.StringResource(R.string.error_something_went_wrong)
                                )
                            )
                        }
                        weatherResource is Resource.Error -> {
                            _uiEvent.send(
                                UiEvent.ShowSnackbar(
                                    message = UiText.StringResource(R.string.error_something_went_wrong)
                                )
                            )
                        }
                        timeResource is Resource.Loading || weatherResource is Resource.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }
                    }
                }
        }
    }
}