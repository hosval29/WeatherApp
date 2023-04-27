package com.hosvalandroiddev.weather_presentation.screens.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.hosvalandroiddev.weather_domain.model.Location

@Composable
fun LocationItem(
    modifier: Modifier = Modifier,
    location: Location,
    onClick: () -> Unit
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable { onClick() }
    ) {

        val (tittle, helper, arrowForward) = createRefs()

        Text(
            text = location.name ?: "",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .constrainAs(tittle) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(arrowForward.start, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = location.state ?: location.country ?: "",
            fontSize = 14.sp,
            color = Color(0xFFC2C5C5),
            modifier = Modifier
                .constrainAs(helper) {
                    top.linkTo(tittle.bottom, margin = 4.dp)
                    start.linkTo(tittle.start)
                }
        )

        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Arrow Forward",
            modifier = Modifier
                .constrainAs(arrowForward) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, margin = 20.dp)
                },
            tint = Color.Black
        )
    }
}