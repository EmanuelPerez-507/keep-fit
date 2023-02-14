package com.example.keepfit.Home.View

import CustomComponent
import android.content.Intent.getIntent
import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
        ButtonWithColor(Modifier.align(Alignment.BottomEnd), SetTotalSteps)
    }

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
//        Icon(painter = painterResource(id = R.drawable.baseline_settings_24),
//            contentDescription = "Settings",
//            tint = Color.Black,
//            modifier = Modifier
//                .size(45.dp)
//                .padding(end = 10.dp),
//        )
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
    value = savedTotalSteps

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
fun ButtonWithColor(mod: Modifier,setStepsFunction:(Int)->Unit){
    Button(
        modifier = mod
            .offset((-10).dp, (-100).dp),
        onClick = {setStepsFunction(passableSteps)},
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray))

    {
        Text(text = "+",color = Color.White)
    }
}


//@Composable
//fun AddRecordButton(
//    setStepsFunction:(Int)->Unit
//){
//Row() {
    
//}
//}

