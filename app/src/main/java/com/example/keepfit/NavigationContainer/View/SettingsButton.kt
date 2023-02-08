package com.example.keepfit.NavigationContainer.View

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.keepfit.R
import com.example.keepfit.ui.theme.CustomShapes
import com.example.keepfit.ui.theme.Purple700
import com.example.keepfit.ui.theme.Transparent

enum class SettingButtonState{
    EXPANDED, COMPRESSED
}

@Composable
fun SettingsButton(alignment: Modifier) {

    var currentState: SettingButtonState by remember{
        mutableStateOf(SettingButtonState.COMPRESSED)
    }

    val animatedPadding: Dp by animateDpAsState(
        targetValue = if(currentState==SettingButtonState.COMPRESSED)
            10.dp
            else
                0.dp
    )

    val animatedRotation: Float by animateFloatAsState(
        targetValue = if(currentState==SettingButtonState.COMPRESSED)
            0F
            else
                -90F,
        animationSpec = spring(dampingRatio = 0.7F, stiffness = Spring.StiffnessLow)
    )

    val animatedCorner: Int by animateIntAsState(
        targetValue = if(currentState==SettingButtonState.COMPRESSED)
            50
        else
            0,
    )

    val animatedWidth: Dp by animateDpAsState(
        targetValue = if(currentState==SettingButtonState.COMPRESSED)
            55.dp
            else
                250.dp,
        animationSpec = spring(dampingRatio = 0.7F, stiffness = Spring.StiffnessLow)
//            LocalConfiguration.current.screenWidthDp.dp,
    )

    val animatedHeight: Dp by animateDpAsState(
        targetValue = if(currentState==SettingButtonState.COMPRESSED)
            55.dp
        else
            450.dp,
        animationSpec = spring(dampingRatio = 0.7F, stiffness = Spring.StiffnessLow)
//            LocalConfiguration.current.screenHeightDp.dp,
    )

    Surface(
        modifier = alignment
            .padding(10.dp)
//            .padding(animatedPadding)
            .width(animatedWidth)
            .height(animatedHeight)
            .clip(RoundedCornerShape(25.dp)),
//            .clip(RoundedCornerShape(animatedCorner)),
        color = Color.Gray,
        elevation = 10.dp - animatedPadding
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ){

            Button(
                modifier = Modifier
//                    .padding(10.dp - animatedPadding)
                    .size(65.dp)
                    .align(Alignment.End)
                    .rotate(animatedRotation)
                    .clip(CustomShapes.round()),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,contentColor = Transparent),
                onClick = {
                    if(currentState == SettingButtonState.COMPRESSED)
                        currentState = SettingButtonState.EXPANDED
                    else
                        currentState = SettingButtonState.COMPRESSED
                },
                elevation = ButtonDefaults.elevation(10.dp - animatedPadding,0.dp)
            ){

                Image(modifier = Modifier.size(65.dp),
                    imageVector =  ImageVector.vectorResource(R.drawable.settings_icon),
                    contentDescription = "Gear icon")

            }

        }


    }

}