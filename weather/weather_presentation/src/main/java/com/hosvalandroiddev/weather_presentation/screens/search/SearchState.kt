package com.hosvalandroiddev.weather_presentation.screens.search

import com.hosvalandroiddev.weather_domain.model.Location

data class SearchState(
    val query: String = "",
    val isLoading: Boolean = false,
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val locations: List<Location> = emptyList()
)
