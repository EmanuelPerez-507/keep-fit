package com.example.keepfit.NavigationContainer.View

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.keepfit.Controls.ExpandableFAB
import com.example.keepfit.R
import com.example.keepfit.Settings.View.SettingsScreen
import com.example.keepfit.TemplateFunctionality.ExpandState
import com.example.keepfit.TemplateFunctionality.Expandable
import com.example.keepfit.ui.theme.CancelRed
import com.example.keepfit.ui.theme.medium

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
    height = 450.dp,
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