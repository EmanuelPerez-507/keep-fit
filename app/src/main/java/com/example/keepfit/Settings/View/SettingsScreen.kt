package com.example.keepfit.Settings.View

import android.widget.Button
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.keepfit.Controls.SegmentedButton
import com.example.keepfit.NavigationContainer.View.SettingsAction

@Composable
fun SettingsScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
        ){

            Text(modifier = Modifier
                .weight(1F)
                .padding(start = 10.dp)
                .align(Alignment.CenterVertically),
                color = Color.White,
                text ="Settings",
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold))

        }

        val sideSpacing = 17.dp

        Spacer(modifier = Modifier.weight(1F))

        Divider(modifier = Modifier.padding(horizontal = sideSpacing))

        SettingsPanel(
            title = stringResource(com.example.keepfit.R.string.settings_title_theme),
            description = stringResource(com.example.keepfit.R.string.settings_description_theme)
        ){
            var themeStatus:Boolean by remember{mutableStateOf(false)}
            Switch(modifier = Modifier.clickable { themeStatus = !themeStatus },
                checked = themeStatus, onCheckedChange = {})
        }

        Divider(modifier = Modifier.padding(horizontal = sideSpacing))

        SettingsPanel(
            title = stringResource(com.example.keepfit.R.string.settings_title_distance),
            description = stringResource(com.example.keepfit.R.string.settings_description_distance)
        ){
            
            SegmentedButton("Km", "Miles")

        }

        Divider(modifier = Modifier.padding(horizontal = sideSpacing))

        SettingsAction(
            title = stringResource(com.example.keepfit.R.string.settings_title_clearData),
            description = stringResource(com.example.keepfit.R.string.settings_description_clearData)
        )

        Divider(modifier = Modifier.padding(horizontal = sideSpacing))

        SettingsAction(
            title = stringResource(com.example.keepfit.R.string.settings_title_resetSettings),
            description = stringResource(com.example.keepfit.R.string.settings_description_resetSettings)
        )

        Divider(modifier = Modifier.padding(bottom = 14.dp, start = sideSpacing, end = sideSpacing))

    }

}