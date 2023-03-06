package com.example.keepfit.NavigationContainer.View

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.keepfit.Controls.ExpandableFAB
import com.example.keepfit.R
import com.example.keepfit.Settings.View.SettingsScreen
import com.example.keepfit.Functionality.Template.ExpandState
import com.example.keepfit.ui.theme.CancelRed

@Composable
fun SettingsButton(
    alignment: Modifier,
    state: ExpandState,
) {

ExpandableFAB(
    compressedOffsetH = (-10).dp,
    compressedOffsetV = 10.dp,
    expandedOffsetH = (-20).dp,
    expandedOffsetV = 20.dp,
    expandedButtonColor = CancelRed,
    FABColor = Color.LightGray,
    height = 150.dp,
    width = 300.dp,
    iconId = R.drawable.settings_icon,
    iconDescriptor = "Gear icon",
    alignment = alignment,
    state = state,
    anchor = Alignment.TopEnd
){

    SettingsScreen()

}

}