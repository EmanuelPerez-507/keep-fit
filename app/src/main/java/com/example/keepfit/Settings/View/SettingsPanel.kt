package com.example.keepfit.Settings.View

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun SettingsPanel(title:String, description:String, control:@Composable ()->Unit = {}) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(ButtonDefaults.ContentPadding),
    ){
        Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){

            Column(modifier = Modifier.weight(1F),
                horizontalAlignment = Alignment.Start)
            {
                Text(title, color = Color.White, style = MaterialTheme.typography.h6)
                Text(description, color = Color.Gray, style = MaterialTheme.typography.body2)
            }

            control.invoke()

        }

    }

}