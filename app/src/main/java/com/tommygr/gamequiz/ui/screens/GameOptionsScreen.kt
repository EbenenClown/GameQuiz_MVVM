package com.tommygr.gamequiz.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tommygr.gamequiz.R
import com.tommygr.gamequiz.ui.composables.StandardButton

@Composable
fun GameOptionsScreen(navController: NavController) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD5663B)), verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenWidth.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.White, Color(
                                0xFFD5663B
                            )
                        )
                    )
                )
        )

        {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height((screenWidth / 1.5).dp)
                    .width((screenWidth / 1.5).dp)
                    .align(Alignment.Center)
            )
        }
        AnimatedVisibility(
            visibleState = visibleState,
            enter = slideInHorizontally(
                animationSpec = tween(
                    durationMillis = 500
                ),
                initialOffsetX = {
                    -screenWidth * 3
                },
            )
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.height((screenHeight * (1f / 3f)).dp)
            ) {
                StandardButton("10 Questions") {

                }

                StandardButton("20 Questions") {

                }

                StandardButton("Endless Mode") {

                }
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameOptionsPreview() {
    GameOptionsScreen(rememberNavController())
}