package com.tommygr.gamequiz.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tommygr.gamequiz.ui.screens.GameScreen

@Composable
fun StandardButton(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black,
    textAlign: TextAlign = TextAlign.End,
    onClicked: () -> Unit = {}
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
            textAlign = textAlign,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AttemptCheckBox(
    checked: Boolean,
    modifier: Modifier = Modifier
) {
    IconButton(modifier = modifier.border(border = BorderStroke(2.dp, Color.White)).size(16.dp), onClick = {}) {
        if (checked) {
            Icon(
                Icons.Filled.Clear,
                tint = Color.White,
                contentDescription = "checked",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}