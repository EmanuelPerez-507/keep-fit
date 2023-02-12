package com.example.keepfit.Home.View

import android.content.Intent.getIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
var passableSteps = 100
var savedTotalSteps = 0
var goalStepsToday = 1700
var hitStepsGoal = ""

@Composable
@Preview
fun HomeScreen() {
    var Steps by remember { mutableStateOf("") }

    var totalStepsToday:Int by remember {
        mutableStateOf(0)
    }

    totalStepsToday = savedTotalSteps

    val SetTotalSteps = fun (steps:Int){
        println("Added $passableSteps steps!")
        totalStepsToday += passableSteps
        savedTotalSteps = totalStepsToday
        println("Total steps $totalStepsToday")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(1.dp, Color(0x00ff00))
    )
    {

        Text(
            modifier = Modifier.align(Alignment.TopStart),
            text = "\nToday" +
                    "\n$formatted"
        )

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Steps" +
                    "\n$totalStepsToday" +
                    "\n$goalStepsToday" +
                    "\n"
        )

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = ""
        )

        AddRecordButton(Modifier.align(Alignment.BottomEnd), SetTotalSteps)

    }
}

@Composable
fun AddRecordButton(alignment: Modifier, setStepsFunction:(Int)->Unit) {
    FloatingActionButton(
        modifier = alignment
            .padding(10.dp)
            .size(55.dp)
            .clip(CustomShapes.round()),
        onClick = { setStepsFunction(passableSteps)},
        elevation = FloatingActionButtonDefaults.elevation(0.dp,0.dp),
        backgroundColor = Color.Green
    ) {

        Box(
            modifier = Modifier.padding(15.dp)
        ) {

            Image(painterResource(id = R.drawable.settings_icon), "Gear icon")

        }
    }
}