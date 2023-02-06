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
import com.example.keepfit.NavigationContainer.View.BottomNavItem
import com.example.keepfit.NavigationContainer.View.SettingsButton
import com.example.keepfit.ui.theme.CustomShapes
import com.example.keepfit.ui.theme.KeepFitTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun NavigationContainer() {

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

                   //Some control for navigation

                    SettingsButton(Modifier.align(Alignment.TopEnd))

               }

               BottomNavigation(
                   elevation = 40.dp,
                   modifier = Modifier
                       .padding(horizontal = 10.dp)
                       .clip(shape = CustomShapes.onlyTop.medium())
                       .fillMaxWidth()
               ) {

                   BottomNavItem(title = "Home")
                   BottomNavItem(title = "Goals")
                   BottomNavItem(title = "History")

               }

           }

        }
    }

}