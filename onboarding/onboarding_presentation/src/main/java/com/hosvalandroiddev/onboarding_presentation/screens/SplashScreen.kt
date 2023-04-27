package com.hosvalandroiddev.onboarding_presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hosvalandroiddev.core.navigation.Route
import com.hosvalandroiddev.onboarding_presentation.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.weather_loading))
    val progress by animateLottieCompositionAsState(composition)

    LaunchedEffect(true) {
        delay(5000L)
        navController.popBackStack()
        navController.navigate(Route.MAP_SCREEN)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress }
            )
        }
    }
}