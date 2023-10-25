package com.tommygr.gamequiz.ui.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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