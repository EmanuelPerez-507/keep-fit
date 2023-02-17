package com.example.keepfit.Home.View

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.keepfit.Controls.ExpandableFAB
import com.example.keepfit.Home.ViewModel.ExpandableAddStepsVM
import com.example.keepfit.R
import com.example.keepfit.Settings.View.SettingsScreen
import com.example.keepfit.TemplateFunctionality.Expandable
import com.example.keepfit.ui.theme.CancelRed
import com.example.keepfit.ui.theme.medium

@Composable
fun PlusButton(
    alignment:Modifier,
    state: ExpandableAddStepsVM,
    content:@Composable ()->Unit
) {

    ExpandableFAB(
        expandedButtonColor = CancelRed,
        FABColor = Color.DarkGray,
        height = 200.dp,
        width = 200.dp,
        iconId = R.drawable.settings_icon,
        iconDescriptor = "Plus icon",
        alignment = alignment,
        state = state.expandable(),
        anchor = Alignment.BottomEnd
    ){

        Text("Potato")

    }

}