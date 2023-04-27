package com.hosvalandroiddev.weather_presentation.screens.map

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.hosvalandroiddev.core.R
import com.hosvalandroiddev.core.util.UiEvent
import com.hosvalandroiddev.weather_domain.model.Location
import com.hosvalandroiddev.weather_presentation.screens.map.compontens.MapContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    snackbarHostState: SnackbarHostState,
    location: Location?,
    viewModel: MapViewModel = hiltViewModel(),
    onNavigateToSearch: () -> Unit,
    onFinishApp: () -> Unit
) {
    val state = viewModel.state
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    var skipPartiallyExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )

    val cameraPosition = CameraPositionState(
        position = CameraPosition.fromLatLngZoom(
            state.latLng,
            5f
        )
    )

    BackHandler {
        if (bottomSheetState.isVisible) {
            scope.launch {
                bottomSheetState.hide()
            }
        } else {
            onFinishApp()
        }
    }

    LaunchedEffect(context) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    LaunchedEffect(location) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
            if(location == null) return@repeatOnLifecycle
            if (!location.name.isNullOrEmpty()) {
                viewModel.onEvent(
                    MapEvent.OnSetLocationCity(
                        LatLng(
                            location.latitude ?: 0.0,
                            location.longitude ?: 0.0
                        )
                    )
                )
                openBottomSheet = !openBottomSheet
            }
        }
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (search, googleMap) = createRefs()

        GoogleMap(modifier = Modifier
            .constrainAs(googleMap) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            cameraPositionState = cameraPosition,
            onMapClick = { latLng ->

            }
        ) {
            if (state.latLng != LatLng(0.0, 0.0)) {
                Marker(
                    state = MarkerState(state.latLng),
                    title = "${state.latLng}"
                )
            }
        }

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .clickable { onNavigateToSearch() }
                .height(50.dp)
                .constrainAs(search) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {

                val (search_icon, query, close) = createRefs()

                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = stringResource(id = R.string.description_location),
                    modifier = Modifier
                        .size(24.dp)
                        .constrainAs(search_icon) {
                            start.linkTo(parent.start, margin = 16.dp)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                )

                Text(
                    text = location?.name ?: stringResource(id = R.string.look_climate_locality),
                    modifier = Modifier.constrainAs(query) {
                        top.linkTo(parent.top)
                        start.linkTo(search_icon.end, margin = 8.dp)
                        end.linkTo(close.start, margin = 8.dp)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    }
                )

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search),
                    modifier = Modifier
                        .size(24.dp)
                        .constrainAs(close) {
                            end.linkTo(parent.end, margin = 16.dp)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                )
            }
        }
    }

    if(openBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = rememberModalBottomSheetState()
        ) {
            MapContent(
                locationWeather = state.locationWeather,
                timesTamp = state.timeStamp ?: ""
            )
        }
    }
}