package com.example.keepfit.NavigationContainer.View

import android.view.MotionEvent
import androidx.compose.animation.core.*
import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.keepfit.NavigationContainer.PlaceHolder
import com.example.keepfit.NavigationContainer.SettingButtonState
import com.example.keepfit.R
import com.example.keepfit.ui.theme.CustomShapes
import com.example.keepfit.ui.theme.Transparent
import com.example.keepfit.ui.theme.medium

@Composable
fun SettingsButton(
    alignment: Modifier,
    rectPlaceHolder: PlaceHolder<Rect?>,
    state: SettingButtonState,
    changeState: (SettingButtonState) -> Unit
) {

    val transition = updateTransition(state, label = "settingButtonTransition")

    val animatedPadding: Dp by transition.animateDp(label = "paddingTransition") { currentState ->
        when (currentState) {
            SettingButtonState.COMPRESSED -> 10.dp
            SettingButtonState.EXPANDED -> 20.dp
        }
    }

    val animatedRotation: Float by transition.animateFloat(label = "rotationTransition") { currentState ->
        when(currentState){
            SettingButtonState.COMPRESSED -> 0F
            SettingButtonState.EXPANDED -> -90F
        }
    }

    val animatedCorner: Dp by transition.animateDp(
        transitionSpec = {when{
            SettingButtonState.EXPANDED isTransitioningTo SettingButtonState.COMPRESSED ->
                tween(durationMillis = DefaultDurationMillis/2 ,delayMillis = DefaultDurationMillis/2)
            else -> tween(durationMillis = 3)
        }},
        label = "cornerTransition") { currentState ->
        when(currentState){
            SettingButtonState.COMPRESSED -> 55.dp
            SettingButtonState.EXPANDED -> medium
        }
    }

    val animatedButtonTraversalCorners: Dp by transition.animateDp (label = "traversalCorners"){ currentState ->
        when(currentState){
            SettingButtonState.COMPRESSED -> 55.dp
            SettingButtonState.EXPANDED -> 0.dp
        }
    }

    val animatedWidth: Dp by transition.animateDp(
//        transitionSpec = { spring(dampingRatio = 0.7F, stiffness = Spring.StiffnessLow) },
        label = "widthTransition") { currentState ->
        when(currentState){
            SettingButtonState.COMPRESSED -> 55.dp
            SettingButtonState.EXPANDED -> 250.dp
        }
    }

    val animatedHeight: Dp by transition.animateDp(
//        transitionSpec = { spring(dampingRatio = 0.7F, stiffness = Spring.StiffnessLow) },
        label = "widthTransition") { currentState ->
        when(currentState){
            SettingButtonState.COMPRESSED -> 55.dp
            SettingButtonState.EXPANDED -> 450.dp
        }
    }

    val animatedShadow: Dp by transition.animateDp (label = "shadowTransition"){ currentState ->
        when(currentState){
            SettingButtonState.COMPRESSED -> 8.dp
            SettingButtonState.EXPANDED -> 20.dp
        }
    }

    Surface(
        modifier = alignment
            .padding(animatedPadding)
            .width(animatedWidth)
            .height(animatedHeight)
            .shadow(animatedShadow, shape = RoundedCornerShape(animatedCorner))
            .zIndex(1F)
//            .clip(RoundedCornerShape(animatedCorner))
            .onGloballyPositioned { layoutCoordinates ->
                rectPlaceHolder.payload = layoutCoordinates.boundsInRoot()
            },
//            .clip(RoundedCornerShape(animatedCorner)),
        color = Color.LightGray,
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ){

            Button(
                modifier = Modifier
                    .size(65.dp)
                    .align(Alignment.End),
                shape = RoundedCornerShape(
                        bottomStart = 30.dp
                    ),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,contentColor = Color.Gray),
                onClick = {
                    when(state){
                        SettingButtonState.COMPRESSED->changeState(SettingButtonState.EXPANDED)
                        SettingButtonState.EXPANDED->changeState(SettingButtonState.COMPRESSED)
                    }
                },
            ){

                Image(modifier = Modifier.size(65.dp).rotate(animatedRotation),
                    imageVector =  ImageVector.vectorResource(R.drawable.settings_icon),
                    contentDescription = "Gear icon")

            }

        }


    }

}