package com.example.keepfit.Settings.View

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.keepfit.NavigationContainer.View.SettingsAction

@Composable
fun SettingsScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ){

        Row(
            modifier = Modifier.fillMaxWidth().height(65.dp)
        ){

            Text(modifier = Modifier
                .weight(1F)
                .padding(start = 10.dp)
                .align(Alignment.CenterVertically),
                color = Color.White,
                text ="Settings")

        }

        Spacer(modifier = Modifier.weight(1F))

        Divider(modifier = Modifier.padding(horizontal = 17.dp))

        SettingsAction(title = "option4", description = "description4")

        Divider(modifier = Modifier.padding(horizontal = 17.dp))

        SettingsAction(title = "option4", description = "description4")

        Divider(modifier = Modifier.padding(horizontal = 17.dp))

        SettingsAction(title = "option4", description = "description4")

        Divider(modifier = Modifier.padding(horizontal = 17.dp))

        SettingsAction(title = "option4", description = "description4")

        Divider(modifier = Modifier.padding(bottom = 14.dp, start = 7.dp, end = 7.dp))

    }

}