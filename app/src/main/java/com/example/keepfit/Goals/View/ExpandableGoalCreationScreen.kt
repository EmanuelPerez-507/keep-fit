package com.example.keepfit.Goals.View

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.keepfit.Goals.ViewModel.Create.ExpandableGoalCreateModel
import com.example.keepfit.R
import com.example.keepfit.animationDelay
import com.example.keepfit.ui.theme.ButtonOrange
import com.example.keepfit.ui.theme.CustomShapes
import com.example.keepfit.ui.theme.HeaderOrange

@Composable
fun ExpandableGoalCreationScreen(
    state:ExpandableGoalCreateModel
){

    val createGoalDialogTransition = updateTransition(targetState = state.expandable().expanded,
        label = "createGoalTransition"
    )

    val shadowAnimation: Dp by createGoalDialogTransition.animateDp(
        label="createGoalShadow",
        transitionSpec = {
            tween(
                delayMillis =
                when(state.expandable().expanded){
                    false -> animationDelay
                    true -> 0
                }
            )
        }
    ) {currentState->
        when(currentState){
            false -> 0.dp
            true -> 35.dp
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                shadowAnimation,
                shape = CustomShapes.onlyBottom.medium
            )
            .clip(CustomShapes.onlyBottom.medium)
            .zIndex(0.5F)
            .background(color = Color.White),
    ){
        val alphaAnimation:Float by createGoalDialogTransition.animateFloat(
            label="createGoalAlpha",
            transitionSpec = {
                tween(
                    delayMillis =
                    when(state.expandable().expanded){
                        false -> 0
                        true -> animationDelay
                    }
                )
            }
        ) {currentState ->
            when(currentState){
                false -> 0F
                true -> 1F
            }
        }
        val heightAnimation:Dp by createGoalDialogTransition.animateDp(
            label="createGoalHeight",
            transitionSpec = {
                tween(
                    delayMillis =
                    when(state.expandable().expanded){
                        false -> animationDelay
                        true -> 0
                    }
                )
            }
        ) { currentState ->
            when(currentState){
                false -> 0.dp
                true -> 200.dp
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(heightAnimation)
                .alpha(alphaAnimation),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Button(
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .clip(shape = RectangleShape),
                shape = RectangleShape,
                onClick = {
                    state.expandable().expanded = false
                }
            ){

                Column{
                    Text(text = "Cancel", style = MaterialTheme.typography.h6)
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
            TextField(value = state.goalName, onValueChange = state::goalName::set)
            Spacer(modifier = Modifier.height(10.dp))
            TextField(value = state.goalSteps, onValueChange = state::goalSteps::set)
        }

        val buttonScale:Float by createGoalDialogTransition.animateFloat(
            label = "rocketButtonAnimation",
            transitionSpec = {
                tween(
                    delayMillis =
                    when(state.expandable().expanded){
                        false -> animationDelay
                        true -> 0
                    }
                )
            }
            ) { currentState->
            when(currentState){
                false->1.0f
                true->0.7f
            }
        }

//        Spacer(modifier = Modifier.weight(1f))

        Surface(
            modifier = Modifier.scale(buttonScale)
        ){

            GoalButton(
                Modifier.align(Alignment.CenterHorizontally),
                createGoalDialogTransition,
                state
            )

        }

    }

}

//Button to set the goals(BUTTON: add clickable or onclick)
@Composable
fun GoalButton(modifier: Modifier,
               animationTransition: Transition<Boolean>,
               currentPanelState: ExpandableGoalCreateModel){

    val rotationAnimation:Float by animationTransition.animateFloat(
        label = "rocketRotation",
        transitionSpec = {
            tween(
                delayMillis =
                when(currentPanelState.expandable().expanded){
                    false -> animationDelay
                    true -> 0
                }
            )
        }
    ) {currentValue ->
        when(currentValue){
            true -> -45F
            false -> 0F
        }
    }

    Row(
        modifier = modifier
            .height(150.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .height(120.dp)
                .padding(top = 15.dp, bottom = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Button(
                shape = CustomShapes.round(),
                onClick = currentPanelState::expansionBtnClick,
                contentPadding = PaddingValues(0.dp)
            ){

                Box( contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(ButtonOrange)) {

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .background(HeaderOrange)

                    ) {
                        Icon(
                            contentDescription ="Rocket",
                            tint = Color.Unspecified,
                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_rocket_launch_24),
                            modifier = Modifier
                                .size(75.dp)
                                .rotate(rotationAnimation)
                        )
                    }
                }

            }

            Text(
                text = "Tap to Create",
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(start = 0.dp)


            )
        }


    }


}