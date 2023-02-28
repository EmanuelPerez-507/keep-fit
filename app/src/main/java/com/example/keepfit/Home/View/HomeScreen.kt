package com.example.keepfit.Home.View

import CustomComponent
//import android.content.Intent.getIntent
//import android.graphics.Paint.Align
//import androidx.compose.animation.core.animateDpAsState
//import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
//import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
//import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
//import androidx.compose.ui.modifier.modifierLocalOf
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
//import androidx.core.content.ContextCompat.startActivity
import com.example.keepfit.Home.ViewModel.ExpandableAddStepsVM
import com.example.keepfit.Home.ViewModel.HomeVM
import com.example.keepfit.ui.theme.*
//import com.example.keepfit.NavigationContainer.View.Screen
//import com.example.keepfit.R
//import com.example.keepfit.Settings.View.SettingsScreen
//import com.example.keepfit.ui.theme.CustomShapes
//import com.example.keepfit.ui.theme.HeaderOrange
//import com.google.accompanist.insets.LocalWindowInsets
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

val current = LocalDateTime.now()
val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
val formatted = current.format(formatter)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(state:HomeVM, plusButtonState:ExpandableAddStepsVM)
{
    val constantPadding:Dp = 80.dp
    val imePadding:Dp = WindowInsets.ime.asPaddingValues().calculateBottomPadding()-
        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val inputPadding = max(constantPadding, imePadding + 0.dp)

    Box(
        modifier = Modifier
//            .let {
//                if (imePadding > constantPadding) it.imePadding()
//                else it
//            }
            .padding(bottom = inputPadding)
            .fillMaxSize()
            .background(
                MainBack
            )
    ){

        Column (
            modifier = Modifier.fillMaxSize()
                ){
            //call functions
            dateAndTime()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                CustomComponent(
                    projectionIndicatorValue = state.projectionSteps,
                    indicatorValue = state.currentSteps,
                    maxIndicatorValue = 10000
                )
            }

        }

        PlusButton(
            alignment = Modifier.align(Alignment.BottomEnd),
            state = plusButtonState)
        canvasBottom(state)

    }
}

fun PassThrough(){

}

@Composable
fun dateAndTime() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Text(

            text = ("\nToday" +
                    "\n$formatted"),

            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 10.dp, bottom = 20.dp)
        )
    }

}

@Composable
fun canvasBottom(state: HomeVM) {
Box(modifier = Modifier
    .padding(top = 400.dp)
    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
    .shadow(
        elevation = 20.dp,
//        shape = RoundedCornerShape(8.dp),
    )
){
    Row(
        modifier = Modifier
            .background(Color.White)

            .height(250.dp)
            .padding(0.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(10.dp))
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                )
                .height(30.dp)
                .background(LightBlue)
                .clickable(onClick = { /* navigate to screen 1 */ }),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Calories Burned",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),

                )
                Text(
                    text = "${state.currentCalories}",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White

                    ),

                )
                Text(
                    text = "Kcal",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.Gray


                    ),

                    )
            }
        }

        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(10.dp))
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                )
                .background(ButtonOrange)
                .clickable(onClick = { /* navigate to screen 2 */ }),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Active Goal",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),

                    )
                Text(
                    text = "Name",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,

                        ),

                    )
            }
        }

        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(10.dp))
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                )
                .background(LightBlue)
                .clickable(onClick = { /* navigate to screen 3 */ }),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Total Distance",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold
                    ),

                    )
                Text(
                    text = "${state.currentDistance}",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White

                        ),
                    )
                Text(
                    text = "miles",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.Gray


                    ),

                    )

            }
        }
    }
}

}




