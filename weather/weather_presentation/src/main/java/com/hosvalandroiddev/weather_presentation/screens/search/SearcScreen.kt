package com.hosvalandroiddev.weather_presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hosvalandroiddev.core.R
import com.hosvalandroiddev.core.util.UiEvent
import com.hosvalandroiddev.weather_domain.model.Location
import com.hosvalandroiddev.weather_presentation.components.ProgressDialog
import com.hosvalandroiddev.weather_presentation.screens.search.components.LocationItem
import com.hosvalandroiddev.weather_presentation.screens.search.components.SearchTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: SearchViewModel = hiltViewModel(),
    onNextClickUp: () -> Unit,
    onShowLocationInMap: (Location) -> Unit
) {

    val state = viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = keyboardController) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                    keyboardController?.hide()
                }
                else -> Unit
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        SearchTextField(
            text = state.query,
            shouldShowHint = state.isHintVisible,
            onValueChange = {
                viewModel.onEvent(SearchEvent.OnQueryChange(it))
            },
            onSearch = {
                keyboardController?.hide()
                viewModel.onEvent(SearchEvent.OnSearch)
            },
            onFocusChanged = {
                viewModel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
            },
            onNavigateUp = { onNextClickUp() }
        )

        Spacer(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .height(8.dp)
                .background(Color(0xFFe8e9eb))
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.result_search_success),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            itemsIndexed(state.locations) { index, location ->
                LocationItem(location = location) { onShowLocationInMap(location) }
            }
        }
    }

    if (state.isLoading) {
        ProgressDialog()
    }

    if (!state.isLoading && state.locations.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = if (state.query.isBlank()) R.string.result_search_first_time else R.string.result_search_error),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color(0xFF004A50)
            )
        }
    }
}