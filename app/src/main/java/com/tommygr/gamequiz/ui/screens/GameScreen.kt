package com.tommygr.gamequiz.ui.screens

import android.graphics.Paint
import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tommygr.gamequiz.ui.composables.AttemptCheckBox
import com.tommygr.gamequiz.ui.composables.StandardButton

@Composable
fun GameScreen() {
    Column(modifier = Modifier.fillMaxSize()) {

        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier
                        .size(48.dp)
                        .weight(1f)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.ArrowForward,
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier
                        .size(48.dp)
                        .weight(1f)
                )
            }
        }

        Text(
            text = "1/20",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AttemptCheckBox(checked = false)
            AttemptCheckBox(checked = true)
            AttemptCheckBox(checked = true)
        }

        CountDownProgressIndicator(
            modifier = Modifier.padding(
                start = 64.dp,
                top = 8.dp,
                end = 64.dp
            ), progress = 0.3f
        )

        Text(
            text = "Who is synchronising the main role as Mario in the new Mario Brothers Movie?",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 32.dp, start = 32.dp, end = 32.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Column(modifier = Modifier.height(360.dp), verticalArrangement = Arrangement.SpaceEvenly) {
            QuestionButton(text = "A.   Albert Richi")

            QuestionButton(text = "B.   Susan Boles")

            QuestionButton(text = "C.   Kiev Gaurenzo")

            QuestionButton(text = "D.   Chris Pratt")
        }


    }
}

@Composable
fun QuestionButton(modifier: Modifier = Modifier, text: String) {
    StandardButton(text = text, modifier = modifier.height(65.dp), textAlign = TextAlign.Start)
}

@Composable
fun CountDownProgressIndicator(modifier: Modifier = Modifier, progress: Float) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .height(24.dp)
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.DarkGray)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameScreenPreview() {
    GameScreen()
}