package com.tommygr.gamequiz.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tommygr.gamequiz.R
import com.tommygr.gamequiz.ui.ui.composables.StandardButton


@Composable
fun MainScreen(onClicked: () -> Unit) {
    val offsetX = remember { Animatable(-575f) }

    LaunchedEffect(Unit) {
        offsetX.animateTo(0f, animationSpec = tween(durationMillis = 1000, easing = LinearEasing))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(255, 231, 135, 255),
                        Color(255, 130, 53)
                    )
                )
            )
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxHeight()
                .offset(x = offsetX.value.dp),
            onDraw = { drawCircle(Color.Gray, radius = size.height) })
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            , verticalArrangement = Arrangement.spacedBy(150.dp)
    ) {
        Box() {
            Image(
                modifier = Modifier.padding(top = 50.dp),
                painter = painterResource(id = R.drawable.gamequiz_string),
                contentDescription = "Icon"
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

            StandardButton("Spielen") {

            }

            StandardButton(
                "Statistik", modifier = Modifier
                    .height(50.dp)
                    .padding(PaddingValues(end = 40.dp)), Color.DarkGray
            ) {

            }

            StandardButton(
                "Einstellungen", modifier = Modifier
                    .height(50.dp)
                    .padding(PaddingValues(end = 40.dp)), Color.DarkGray
            ) {

            }

            Text(
                text = "Login",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.padding(PaddingValues(start = 16.dp))
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MainScreen(onClicked = {})
}