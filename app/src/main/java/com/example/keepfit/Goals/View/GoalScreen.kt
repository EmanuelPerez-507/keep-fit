package com.example.keepfit

import android.view.MotionEvent
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.keepfit.Goals.View.ExpandableGoalCreationScreen
import com.example.keepfit.Goals.View.Feature
import com.example.keepfit.Goals.ViewModel.Create.ExpandableGoalCreateModel
import com.example.keepfit.Goals.ViewModel.Show.GoalScreenModel
import com.example.keepfit.ui.theme.*

const val animationDelay:Int = 150

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

            Box(){

                ExpandableGoalCreationScreen(creationViewModel)

                Column(){

                    Spacer(
                        modifier = Modifier.height(135.dp)
                    )
                    //Added features in the parameters to easily change the goals info,
                    // can be used dynamically as well
                    FeatureSection(viewModel,currentGoalList)

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
            ) {
        Column(

//      verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = "Goals",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
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
fun FeatureSection(state:GoalScreenModel ,features: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Your Goals",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(10.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(state = state,feature = features[it])
            }
        }
    }
}
// this composable describes each box with the pattern design with 3 different colors
//
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FeatureItem(
    state: GoalScreenModel,
    feature: Feature,
) {
    val selected:Boolean = feature.id == state.selectedGoalId
    val finalShape = RoundedCornerShape(medium)

    BoxWithConstraints(
        modifier = Modifier
            .let{
                when(selected){
                    true -> it.scale(1.05f)
                    false -> it
                }
            }
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(finalShape)
            .let{
                if(selected)
                    it.border(5.dp, MaterialTheme.colors.secondary, finalShape)
                else
                    it
            }
            .background(feature.darkColor)
            .clickable {
                state.selectedGoalId = feature.id
            }
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

            Row(
                modifier = Modifier.fillMaxWidth()
                , horizontalArrangement = when(selected){
                    true -> Arrangement.SpaceBetween
                    false->Arrangement.Start
                }
            ){

                Text(
                    text = feature.title,
                    style = MaterialTheme.typography.h6,
                    lineHeight = 26.sp,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.Black
                )

                if(selected){
                    Image(modifier = Modifier
                        .size(35.dp)
                        ,imageVector =  ImageVector.vectorResource(R.drawable.check_icon)
                        , colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary)
                        ,contentDescription = "checkMark")
                }

            }



            Text(
                text = feature.steps,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.align(Center),
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
            if(!selected){
                Icon(
                    contentDescription ="Delete",
                    tint = Color.Unspecified,
                    imageVector = ImageVector.vectorResource(id = R.drawable.delete_icon),
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.BottomStart)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colors.primary)
                        .clickable { println("Delete") }
                )
            }

        }
    }
}


