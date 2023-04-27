package com.hosvalandroiddev.weatherapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.hosvalandroiddev.core.navigation.Route
import com.hosvalandroiddev.onboarding_presentation.screens.SplashScreen
import com.hosvalandroiddev.weather_domain.model.Location
import com.hosvalandroiddev.weather_presentation.screens.map.MapScreen
import com.hosvalandroiddev.weather_presentation.screens.search.SearchScreen
import com.hosvalandroiddev.weatherapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {

                val snackbarHostState = remember { SnackbarHostState() }
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        snackbarHost = { SnackbarHost(snackbarHostState) }
                    ) { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = Route.MAP_SCREEN,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        ) {
                            composable(Route.SPLASH_SCREEN) {
                                SplashScreen(navController = navController)
                            }
                            composable(Route.MAP_SCREEN) { backStackEntry ->

                                val searchLocation by backStackEntry
                                    .savedStateHandle
                                    .getLiveData<String>("searchLocation")
                                    .observeAsState()

                                MapScreen(
                                    snackbarHostState = snackbarHostState,
                                    location = Gson().fromJson(
                                        searchLocation,
                                        Location::class.java
                                    ),
                                    onNavigateToSearch = {
                                        backStackEntry.savedStateHandle.remove<String>("searchLocation")
                                        navController.navigate(Route.SEARCH_SCREEN)
                                    },
                                    onFinishApp = { finish() }
                                )
                            }
                            composable(Route.SEARCH_SCREEN) {
                                SearchScreen(
                                    snackbarHostState = snackbarHostState,
                                    onNextClickUp = {
                                        navController.popBackStack()
                                    },
                                    onShowLocationInMap = {
                                        navController.previousBackStackEntry?.savedStateHandle?.set(
                                            "searchLocation",
                                            Gson().toJson(it)
                                        )
                                        navController.popBackStack()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}