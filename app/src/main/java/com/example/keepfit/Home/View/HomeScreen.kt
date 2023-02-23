package com.example.keepfit.Home.View

import CustomComponent
import android.content.Intent.getIntent
import android.graphics.Paint.Align
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.core.content.ContextCompat.startActivity
import com.example.keepfit.Home.ViewModel.ExpandableAddStepsVM
import com.example.keepfit.Home.ViewModel.HomeVM
import com.example.keepfit.NavigationContainer.View.Screen
import com.example.keepfit.R
import com.example.keepfit.Settings.View.SettingsScreen
import com.example.keepfit.ui.theme.CustomShapes
import com.example.keepfit.ui.theme.HeaderOrange
import com.google.accompanist.insets.LocalWindowInsets
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

val current = LocalDateTime.now()
val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
val formatted = current.format(formatter)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    state:HomeVM,
    plusButtonState:ExpandableAddStepsVM
) {

    val constantPadding:Dp = 80.dp
    val imePadding:Dp = WindowInsets.ime.asPaddingValues().calculateBottomPadding()-
        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val inputPadding = max(constantPadding, imePadding + 10.dp)

    Box(
        modifier = Modifier
//            .let {
//                if (imePadding > constantPadding) it.imePadding()
//                else it
//            }
            .padding(bottom = inputPadding)
            .fillMaxSize()
            .background(color = Color.White)
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
                println("V${state.projectionSteps}")
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

    }

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

