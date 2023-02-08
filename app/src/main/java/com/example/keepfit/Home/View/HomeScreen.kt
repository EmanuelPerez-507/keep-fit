package com.example.keepfit.Home.View

import android.content.Intent.getIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Updater
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.keepfit.NavigationContainer.View.Screen
import com.example.keepfit.R
import com.example.keepfit.ui.theme.CustomShapes
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

val current = LocalDateTime.now()
val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
val formatted = current.format(formatter)
var totalStepsToday = 0
var goalStepsToday = 0

@Composable
@Preview
fun HomeScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(1.dp, Color(0x00ff00))
    )
    {

        Text(
            modifier = Modifier.align(Alignment.TopStart),
            text = "Today\n$formatted"
        )


        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "$totalStepsToday"
        )

        AddRecordButton(Modifier.align(Alignment.BottomEnd))
    }
}

fun AddNumberSteps(){
    println("Added 100 steps!")
    totalStepsToday += 100
    println("Total steps $totalStepsToday")
}

@Composable
fun AddRecordButton(alignment: Modifier) {
    FloatingActionButton(
        modifier = alignment
            .padding(10.dp)
            .size(55.dp)
            .clip(CustomShapes.round()),
        onClick = {AddNumberSteps()},
        elevation = FloatingActionButtonDefaults.elevation(0.dp,0.dp),
        backgroundColor = Color.Gray
    ) {

        Box(
            modifier = Modifier.padding(15.dp)
        ) {

            Image(painterResource(id = R.drawable.settings_icon), "Gear icon")

        }
    }
}