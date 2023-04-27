package com.hosvalandroiddev.weather_presentation.screens.map

import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {
    data class OnSetLocationCity(val latLng: LatLng, val boolean: Boolean? = null) : MapEvent()
}
