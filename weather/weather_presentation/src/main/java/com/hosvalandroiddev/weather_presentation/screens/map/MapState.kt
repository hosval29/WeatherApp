package com.hosvalandroiddev.weather_presentation.screens.map

import com.google.android.gms.maps.model.LatLng
import com.hosvalandroiddev.weather_domain.model.LocationWeather

data class MapState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val locationWeather: LocationWeather = LocationWeather(),
    val latLng: LatLng = LatLng(0.0, 0.0),
    val stateLatLng: Boolean? = false,
    val timeStamp: String? = "2023-02-13 15:10:05"
)
