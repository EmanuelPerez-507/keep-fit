package com.example.keepfit.Goals.View

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun GoalsScreen() {

    Box(
        modifier = Modifier.fillMaxSize().border(1.dp, Color(0x00ff00))
    ){

        Text(modifier = Modifier.align(Center),
            text ="Goals Screen")

    }
    
}