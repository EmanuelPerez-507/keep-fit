package com.example.keepfit.NavigationContainer

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.keepfit.Goals.View.GoalsScreen
import com.example.keepfit.History.View.HistoryScreen
import com.example.keepfit.Home.View.HomeScreen
import com.example.keepfit.NavigationContainer.View.Screen
import com.example.keepfit.NavigationContainer.View.SettingsButton
import com.example.keepfit.ui.theme.CustomShapes
import com.example.keepfit.ui.theme.KeepFitTheme

@Composable
fun NavigationContainer() {
    
    val navController = rememberNavController()
    
    KeepFitTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {

           Column() {

               Box(
                   modifier = Modifier
                       .fillMaxWidth()
                       .weight(1F)
               ) {

                   NavHost(navController = navController,
                       startDestination = Screen.Home.route){

                       composable(Screen.Home.route){ HomeScreen() }

                       composable(Screen.Goals.route){ GoalsScreen() }

                       composable(Screen.History.route){ HistoryScreen() }

                   }

                    SettingsButton(Modifier.align(Alignment.TopEnd))

               }

               BottomNavigation(
                   elevation = 40.dp,
                   modifier = Modifier
                       .padding(horizontal = 10.dp)
                       .clip(shape = CustomShapes.onlyTop.medium())
                       .fillMaxWidth()
               ) {

                   val navBackStackEntry by navController.currentBackStackEntryAsState()
                   val currentDestination = navBackStackEntry?.destination

                    Screen.all.map {
                        itemData -> BottomNavigationItem(
                            label = {Text(text = itemData.label)},
                            icon =  {Icon(painterResource(id = itemData.icon), contentDescription = null)},
                            selected = currentDestination?.hierarchy?.any { it.route == itemData.route  } == true,
                            onClick = {
                                      navController.navigate(itemData.route)
                            },
                        )
                    }


               }

           }

        }
    }

}