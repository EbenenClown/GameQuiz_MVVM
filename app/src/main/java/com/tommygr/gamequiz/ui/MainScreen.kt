package com.tommygr.gamequiz.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tommygr.gamequiz.R
import com.tommygr.gamequiz.ui.ui.composables.StandardButton
import kotlinx.coroutines.delay


@Composable
fun MainScreen(onClicked: () -> Unit) {
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD5663B))
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 100.dp), verticalArrangement = Arrangement.SpaceEvenly) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(screenWidth.dp)
            .background(brush = Brush.radialGradient( colors = listOf(Color(0xFFFFFFFF), Color(
                0xFFD5663B
            )
            )))
        )

        {
            Image(
                painter = painterResource(id = R.drawable.logo_full_upscaled),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height((screenWidth / 1.5).dp)
                    .width((screenWidth / 1.5).dp)
                    .align(Alignment.Center)
            )
        }

        AnimatedVisibility(
            visibleState = state,
            enter = slideInHorizontally(
                animationSpec = tween(
                    durationMillis = 1500
                ),
                initialOffsetX = {
                    -screenWidth * 3
                }
            )
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                StandardButton("Spielen") {

                }

                StandardButton(
                    "Statistik", modifier = Modifier
                        .height(50.dp)
                        .padding(PaddingValues(end = 40.dp))
                ) {

                }

                StandardButton(
                    "Einstellungen", modifier = Modifier
                        .height(50.dp)
                        .padding(PaddingValues(end = 40.dp))
                ) {

                }

                Text(
                    text = "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.padding(PaddingValues(start = 16.dp))
                )
            }
        }
    }
}

@Composable
private fun animatable(initialValue: Float, endValue: Float): Animatable<Float, AnimationVector1D> {
    val offsetX = remember { Animatable(-1000f) }

    LaunchedEffect(Unit) {
        offsetX.animateTo(
            -575f,
            animationSpec = tween(durationMillis = 1500, easing = LinearEasing)
        )
    }
    return offsetX
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MainScreen(onClicked = {})
}