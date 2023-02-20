package com.example.keepfit

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.keepfit.Goals.View.Feature
import com.example.keepfit.Goals.ViewModel.Create.ExpandableGoalCreateModel
import com.example.keepfit.Goals.ViewModel.Show.GoalScreenModel
import com.example.keepfit.ui.theme.*

val animationDelay:Int = 150

//main Goal screen
//@Preview(showBackground = true)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GoalScreen(
    viewModel: GoalScreenModel,
    creationViewModel: ExpandableGoalCreateModel
){

    val currentGoalList = viewModel.goalsList

    Box(modifier = Modifier

        .fillMaxSize()
    ){
        Column {
            HeaderSett()

            val createGoalDialogTransition = updateTransition(targetState = creationViewModel.expandable().expanded,
                label = "createGoalTransition"
            )
            val keyboardController = LocalSoftwareKeyboardController.current

            Box(){

                val shadowAnimation:Dp by createGoalDialogTransition.animateDp(
                    label="createGoalShadow",
                    transitionSpec = {
                        tween(
                            delayMillis =
                            when(creationViewModel.expandable().expanded){
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
                        .zIndex(0.5F)
                        .background(color = Color.White),
                ){
                    val alphaAnimation:Float by createGoalDialogTransition.animateFloat(
                        label="createGoalAlpha",
                        transitionSpec = {
                            tween(
                                delayMillis =
                                when(creationViewModel.expandable().expanded){
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
                                when(creationViewModel.expandable().expanded){
                                    false -> animationDelay
                                    true -> 0
                                }
                            )
                        }
                    ) { currentState ->
                        when(currentState){
                            false -> 0.dp
                            true -> 160.dp
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(heightAnimation)
                            .alpha(alphaAnimation),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        TextField(value = creationViewModel.goalName, onValueChange = creationViewModel::goalName::set)
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(value = creationViewModel.goalSteps, onValueChange = creationViewModel::goalSteps::set)
                    }
                    GoalButton(
                        Modifier.align(Alignment.CenterHorizontally),
                        createGoalDialogTransition,
                        creationViewModel
                    )
                }

                Column(){

                    Spacer(
                        modifier = Modifier.height(135.dp)
                    )
                    //Added features in the parameters to easily change the goals info,
                    // can be used dynamically as well
                    FeatureSection(currentGoalList)

                }

            }

        }
    }
}

// The header which consist of the name of the page
@Composable
fun HeaderSett(
){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .background(HeaderOrange)
            .height(80.dp)
            ){
    Column(

//      verticalArrangement = Arrangement.Center

    ) {
    Text(
        text = "Goals",
        style = MaterialTheme.typography.h4,
        modifier = Modifier.padding(start = 10.dp)
    )
    }
        //SETTINGS ICON(BUTTON)
//        Icon(painter = painterResource(id = R.drawable.baseline_settings_24),
//            contentDescription = "Settings",
//            tint = Color.Black,
//            modifier = Modifier
//                .size(45.dp)
//                .padding(end = 10.dp),
//            )
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

                Box( contentAlignment = Center,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(ButtonOrange)) {

                    Box(
                        contentAlignment = Center,
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
//HORIZONTAL SLOT FORM WHICH WAS USED ACCORDING TO THE PLAN

//Slots created for each goal set
//@Composable
//fun GoalSlots(
//goalname: String ="Goal1",
//goalstep: String = "1500"
//){
//   Row(
//       verticalAlignment = Alignment.CenterVertically,
//       horizontalArrangement = Arrangement.SpaceBetween,
//       modifier = Modifier
//           .padding(10.dp)
//           .clip(RoundedCornerShape(10.dp))
//           .background(Teal200)
//           .padding(horizontal = 10.dp, vertical = 10.dp)
//           .fillMaxWidth()
//
//
//   ) {
//       Column(
//
//       ) {
//
//           Box(
//               contentAlignment = Alignment.Center,
//               modifier = Modifier
////               .size(80.dp)
//                   .width(120.dp)
//                   .height(50.dp)
//                   .clip(RoundedCornerShape(20.dp))
//                   .background(LightBlue)
//
//           ) {
//               Text(
//                   text = goalname,
//                   style = MaterialTheme.typography.h4,
//                   color = Color.White,
//                modifier = Modifier
//
//               )
//           }
//
//       }
//       Box(
//           contentAlignment = Alignment.Center,
//           modifier = Modifier
//               .size(60.dp)
//               .clip(CircleShape)
//               .background(LightBlue)
//               .padding(10.dp)
//       ){
//           Text(
//               text = goalstep,
//               style = MaterialTheme.typography.body1,
//               color = Color.White,
//               )
//       }
//   }
//
//}

//using a experimental api used to form cells in coloums and rows
//This Composable is used to set a list of features to create a lazyverticalgrid
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeatureSection(features: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Your Goals",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(10.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }
}
// this composable describes each box with the pattern design with 3 different colors
//
@Composable
fun FeatureItem(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .scale(
                when(feature.title){
                    "Blue" -> 1F
                    else -> 0.9F
                }
            )
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.17f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }


        // display everything in the box
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.h6,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart),
                color = Color.Black
            )

            Text(
                text = feature.steps,
                style = MaterialTheme.typography.h4,
                modifier = Modifier .align(Center),
                color = Color.Black
            )
            //CAN ADD DELETE ICON HERE
//            Icon(
//                painter = painterResource(id = feature.iconId),
//                contentDescription = feature.title,
//                tint = Color.White,
//                modifier = Modifier.align(Alignment.BottomStart)
//            )
            Text(
                text = "Edit",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        // Handle the click
                    }
                    .align(Alignment.BottomEnd)
                    .background(ButtonOrange)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
//Add a delete icon (or) keep delete icon after an edit pop up opens after clicking edit
//            Icon(
//                contentDescription ="Delete",
//                tint = Color.Unspecified,
//                imageVector = ImageVector.vectorResource(id = R.drawable.delete_icon),
//                modifier = Modifier
//                    .size(75.dp)
//                    .align(Alignment.BottomEnd)
//            )
        }
    }
}


