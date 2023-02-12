package com.example.keepfit.NavigationContainer.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp



@Composable
fun SettingsAction(title:String, description:String){

    Button(
        modifier = Modifier.fillMaxWidth().clip(shape = RectangleShape),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
        onClick = {}
    ){
        Column(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.Start)
        {
            Text(title)
            Text(description)
        }

    }

}