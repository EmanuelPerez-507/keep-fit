package com.example.keepfit.NavigationContainer

import androidx.compose.foundation.layout.*
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.keepfit.Goals.View.GoalsScreen
import com.example.keepfit.Home.View.HomeScreen
import com.example.keepfit.NavigationContainer.View.BottomNavItem
import com.example.keepfit.NavigationContainer.View.SettingsButton
import com.example.keepfit.ui.theme.CustomShapes
import com.example.keepfit.ui.theme.KeepFitTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

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
                       startDestination = "Home"){

                       composable("Home"){
                           HomeScreen()
                       }

                       composable("Goals"){
                           GoalsScreen()
                       }

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

                   BottomNavItem(title = "Home", onClick = {navController.navigate("Home")})
                   BottomNavItem(title = "Goals", onClick = {navController.navigate("Goals")})
                   BottomNavItem(title = "History", onClick = {})

               }

           }

        }
    }

}