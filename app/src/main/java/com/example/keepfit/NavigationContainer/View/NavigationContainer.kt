package com.example.keepfit.NavigationContainer

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.keepfit.GoalScreen
import com.example.keepfit.Goals.ViewModel.Create.ExpandableGoalCreateModel
import com.example.keepfit.Goals.ViewModel.Show.GoalScreenModel
import com.example.keepfit.History.View.HistoryScreen
import com.example.keepfit.Home.View.HomeScreen
import com.example.keepfit.Home.ViewModel.ExpandableAddStepsVM
import com.example.keepfit.Home.ViewModel.HomeVM
import com.example.keepfit.NavigationContainer.View.Screen
import com.example.keepfit.NavigationContainer.View.SettingsButton
import com.example.keepfit.NavigationContainer.ViewModel.ExpandableSettingsViewModel
import com.example.keepfit.ui.theme.Beige1
import com.example.keepfit.ui.theme.CustomShapes
import com.example.keepfit.ui.theme.KeepFitTheme


@OptIn(ExperimentalComposeUiApi::class)
@Composable
//@Preview
fun NavigationContainer(
    goalsViewModel: GoalScreenModel,
    goalsCreateModel: ExpandableGoalCreateModel,
    homeVM: HomeVM,
    addStepsModel: ExpandableAddStepsVM,
    settingsExpandable:ExpandableSettingsViewModel
) {

    val navController = rememberNavController()
    
    KeepFitTheme {

        Box(
            modifier = Modifier
                .navigationBarsPadding()
                .statusBarsPadding()
                .fillMaxSize()
                .pointerInteropFilter { motionEvent ->
                    when (motionEvent.action) {
                        MotionEvent.ACTION_DOWN -> {
//                            if (!settingsRect.payload?.isEmpty!!) {
//                                if (!settingsRect.payload?.contains(
//                                        Offset(motionEvent.x, motionEvent.y)
//                                    )!!
//                                ) {
//                                    settingsButtonState = SettingButtonState.COMPRESSED
//                                }
//                            }
                        }
                    }
                    false
                }

        ) {

            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                ,
//                    .border(3.dp, Color(0xFFFF0000)),
                navController = navController,
                startDestination = Screen.History.route){

                composable(Screen.Home.route){ HomeScreen(
                    homeVM,
                    addStepsModel
                ) }

                composable(Screen.Goals.route){GoalScreen(
                    goalsViewModel,
                    goalsCreateModel
                ) }

                composable(Screen.History.route){ HistoryScreen() }

            }

            BottomNavigation(
                elevation = 0.dp,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .clip(shape = CustomShapes.onlyTop.medium)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Screen.all.map {
                    itemData ->
                    val selected:Boolean = currentDestination?.hierarchy?.any { it.route == itemData.route  } == true
                    val animatedValue:Float by animateFloatAsState(targetValue =
                        when(selected){
                            true -> 1.3F
                            false -> 1F
                        }
                    )
                    BottomNavigationItem(
                        modifier = Modifier.scale(animatedValue),
                        label = {Text(text = itemData.label)},
                        icon =  {Icon(painterResource(id = itemData.icon), contentDescription = null)},
                        selected = selected,
                        onClick = {
                            navController.navigate(itemData.route)
                        },
                    )
                }

               }

            SettingsButton(
                Modifier.align(Alignment.TopEnd),
                settingsExpandable.expandable(),
            )

        }

    }

}