package com.tommygr.gamequiz.ui

import android.content.res.Resources.Theme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tommygr.gamequiz.R


@Composable
fun MainScreen(onClicked: () -> Unit) {
    var isVisible by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color(255, 231, 135, 255),
                    Color(255, 130, 53)
                )
            )
        )) {
        AnimatedVisibility(visible = true, enter = slideInHorizontally() + fadeIn(), modifier = Modifier.fillMaxWidth()) {

        }
        Canvas(modifier = Modifier
            .fillMaxHeight()
            .offset(x = (-575).dp), onDraw = { drawCircle(Color.Gray, radius = size.height)})
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
            Text(
                text = "Register",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.padding(PaddingValues(start = 16.dp))
            )
        }
    }


}

@Composable
fun StandardButton(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black,
    onClicked: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(PaddingValues(end = 40.dp))
            .offset(x = (-16).dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        border = BorderStroke(6.dp, Color.White),
        shape = RoundedCornerShape(topStartPercent = 0, topEndPercent = 40, bottomEndPercent = 40),
        onClick = onClicked
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MainScreen(onClicked = {})
}