package com.hosvalandroiddev.core.util

sealed class UiEvent {
    object Success: UiEvent()
    object NavigateUp: UiEvent()
    object Navigate: UiEvent()
    data class ShowSnackbar(val message: UiText) : UiEvent()
}
