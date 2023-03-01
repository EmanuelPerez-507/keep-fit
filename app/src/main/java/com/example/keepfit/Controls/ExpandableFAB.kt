package com.example.keepfit.Controls

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.keepfit.Functionality.Template.ExpandState
import com.example.keepfit.ui.theme.CustomShapes
import com.example.keepfit.ui.theme.medium

@Composable
fun ExpandableFAB(
    state: ExpandState,
    iconId:Int,
    iconDescriptor:String,
    alignment: Modifier,
    anchor:Alignment,
    rotation:Float = -90F,

    compressedOffsetV:Dp = 0.dp,
    compressedOffsetH:Dp = 0.dp,
    compressedShape: Shape = CircleShape,
    FABColor:Color,

    expandedOffsetV:Dp = 0.dp,
    expandedOffsetH:Dp = 0.dp,
    expandedShape: Shape = RoundedCornerShape(medium),
    expandedButtonColor:Color,
    height:Dp,
    width:Dp,

    beforeClose:()->Unit = {},
    afterClose:()->Unit = {},
    beforeOpen:()->Unit = {},
    afterOpen:()->Unit = {},

    content:@Composable ()->Unit = {}
) {

    val transition: Transition<Boolean> = updateTransition(state.expanded, label = "settingButtonTransition")

    val animatedOffsetH: Dp by transition.animateDp(label = "paddingTransition") { currentState ->
        when (currentState) {
            false -> compressedOffsetH
            true -> expandedOffsetH
        }
    }

    val animatedOffsetV: Dp by transition.animateDp(label = "paddingTransition") { currentState ->
        when (currentState) {
            false -> compressedOffsetV
            true -> expandedOffsetV
        }
    }

    val animatedRotation: Float by transition.animateFloat(label = "rotationTransition") { currentState ->
        when(currentState){
            false -> 0F
            true -> rotation
        }
    }

    val animatedCorner: Dp by transition.animateDp(
        transitionSpec = {
            when(state.expanded){
                false -> tween(
                    durationMillis = AnimationConstants.DefaultDurationMillis /2 ,
                    delayMillis = AnimationConstants.DefaultDurationMillis /2)
                else -> tween(durationMillis = 3)
            }},
        label = "cornerTransition") { currentState ->
        when(currentState){
            false -> 55.dp
            true -> medium
        }
    }

    val animatedButtonTraversalCorners: Dp by transition.animateDp (label = "traversalCorners"){ currentState ->
        when(currentState){
            false -> 55.dp
            true -> 0.dp
        }
    }

    val animatedColor: Color by transition.animateColor(label = "colorAnimation") { currentState ->
        when(currentState){
            false -> FABColor
            true -> expandedButtonColor
        }
    }

    val animatedWidth: Dp by transition.animateDp(
//        transitionSpec = { spring(dampingRatio = 0.7F, stiffness = Spring.StiffnessLow) },
        label = "widthTransition") { currentState ->
        when(currentState){
            false -> 55.dp
            true -> width
        }
    }

    val animatedHeight: Dp by transition.animateDp(
//        transitionSpec = { spring(dampingRatio = 0.7F, stiffness = Spring.StiffnessLow) },
        label = "widthTransition") { currentState ->
        when(currentState){
            false -> 55.dp
            true -> height
        }
    }

    val animatedShadow: Dp by transition.animateDp (label = "shadowTransition"){ currentState ->
        when(currentState){
            false -> 8.dp
            true -> 20.dp
        }
    }

    val animatedContentScale: Float by transition.animateFloat(label = "shadowTransition") {currentState ->
        when(currentState){
            false -> 0F
            true -> 1F
        }
    }

    val buttonShape: Shape = when(anchor){
        Alignment.TopStart -> RoundedCornerShape(bottomEnd = 15.dp)
        Alignment.TopEnd -> RoundedCornerShape(bottomStart = 15.dp)
        Alignment.BottomStart -> RoundedCornerShape(topEnd = 15.dp)
        Alignment.BottomEnd -> RoundedCornerShape(topStart = 15.dp)
        else->CustomShapes.round()
    }

    Surface(
        shape = when(state.expanded){
            true->expandedShape
            false->compressedShape
                                    }
        ,modifier = alignment
            .offset(animatedOffsetH, animatedOffsetV)
            .width(animatedWidth)
            .height(animatedHeight)
//            .shadow(animatedShadow, shape = RoundedCornerShape(animatedCorner))
            .zIndex(1F)
//            .clip(RoundedCornerShape(animatedCorner))
            .onGloballyPositioned { layoutCoordinates ->
                state.expandedBox = layoutCoordinates.boundsInRoot()
            }
//            .clip(RectangleShape)
//            .background(Color.Cyan)
        ,color = FABColor
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ){

            Box(modifier = Modifier.fillMaxSize().scale(animatedContentScale)){

                content.invoke()

            }

            Button(
                modifier = Modifier
                    .size(65.dp)
                    .align(anchor),
                shape = buttonShape,
                colors = ButtonDefaults
                    .buttonColors(backgroundColor = animatedColor,contentColor = Color.Transparent),
                onClick = {
                    if(state.expanded){
                        beforeClose()
                    }else{
                        beforeOpen()
                    }
                    state.expanded = !state.expanded
                },
            ){

                Image(modifier = Modifier
                    .size(65.dp)
                    .rotate(animatedRotation),
                    imageVector =  ImageVector.vectorResource(iconId),
                    contentDescription = iconDescriptor)

            }

        }

    }

    if(transition.currentState==transition.targetState){
        if(state.expanded){
            afterOpen()
        }else{
            afterClose()
        }
    }

}