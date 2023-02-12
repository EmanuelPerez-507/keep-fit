package com.example.keepfit.NavigationContainer.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp



@Composable
fun SettingsAction(title:String, description:String, action:()->Unit = {}){

    Button(
        modifier = Modifier.fillMaxWidth().clip(shape = RectangleShape),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
        onClick = action
    ){
        Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start)
        {
            Text(title, color = Color.White, style = MaterialTheme.typography.h6)
            Text(description, color = Color.Gray, style = MaterialTheme.typography.body2)
        }

    }

}