package com.example.keepfit.NavigationContainer.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.keepfit.R
import com.example.keepfit.ui.theme.CustomShapes

@Composable
fun SettingsButton(alignment: Modifier) {


        FloatingActionButton(
            modifier = alignment
                .padding(10.dp)
                .size(55.dp)
                .clip(CustomShapes.round()),
            onClick = {},
            elevation = FloatingActionButtonDefaults.elevation()
        ) {

            Box(
                modifier = Modifier.padding(10.dp)
            ){

                Image(painterResource(id = R.drawable.settings_icon), "Gear icon")

            }


        }

}