package com.example.keepfit.Home.View

import CustomComponent
import android.content.Intent.getIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.keepfit.ui.theme.HeaderOrange
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
            .background(Color.White)
    ){
    Column {
        //call functions

        settingButton()
        dateAndTime()
        progressBar()

    }
    }

//// timing and day
//        Text(
//            modifier = Modifier.align(Alignment.TopStart),
//            text = "\nToday" +
//                    "\n$formatted"
//        )
//// total steps
//        Text(
//            modifier = Modifier.align(Alignment.Center),
//            text = "Steps" +
//                    "\n$totalStepsToday" +
//                    "\n$goalStepsToday" +
//                    "\n"
//        )
//
//        Text(
//            modifier = Modifier.align(Alignment.Center),
//            text = ""
//        )
//
//        AddRecordButton(Modifier.align(Alignment.BottomEnd), SetTotalSteps)
//
//    }
}



@Composable
fun settingButton(){
    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
//            .height(120.dp)
            .padding(top = 15.dp, bottom = 0.dp),
//
        horizontalAlignment = Alignment.End,

        ) {
        Icon(painter = painterResource(id = R.drawable.baseline_settings_24),
            contentDescription = "Settings",
            tint = Color.Black,
            modifier = Modifier
                .size(45.dp)
                .padding(end = 10.dp),
        )
    }
    }
}
@Composable
fun dateAndTime(){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .height(80.dp)
    ){
        Text(

            text = ("\nToday" +
                    "\n$formatted"),

            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 10.dp, bottom = 20.dp)
        )
    }
}

@Composable
fun progressBar(){
    var value by remember{ mutableStateOf(0) }

    Row(
        modifier = Modifier

            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()

                .padding(top = 15.dp, bottom = 0.dp),
//
            horizontalAlignment = Alignment.CenterHorizontally,


            ) {

            CustomComponent()
        }
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

//@Composable
//fun AddRecordButton(
//    setStepsFunction:(Int)->Unit
//){
//Row() {
    
//}
//}

