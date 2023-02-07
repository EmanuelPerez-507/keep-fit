package com.example.keepfit.Home.View

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Date

@Composable
fun HomeScreen() {
    val current = LocalDateTime.now()

    val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
    val formatted = current.format(formatter)

    Text("Today\n $formatted")
}


