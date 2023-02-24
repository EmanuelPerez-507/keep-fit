import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.keepfit.ui.theme.ButtonOrange
import com.example.keepfit.ui.theme.CustomShapes
import com.example.keepfit.ui.theme.LLightOrange
import com.example.keepfit.ui.theme.LightOrange


@Composable
fun CustomComponent(
    //canvas on which progress bar will lie
     canvasSize: Dp = 300.dp,
    //minimum value to start the custom component
    indicatorValue: Int = 0,
    //maximum value we can reach in custom component
    maxIndicatorValue: Int = 100,
     //projectionValue
    projectionIndicatorValue:Int = 0,
    backgroundIndicatorColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f),
    backgroundIndicatorStrokeWidth: Float = 50f,
     projectionIndicatorColor: Color = MaterialTheme.colors.secondary,
    foregroundIndicatorColor: Color = MaterialTheme.colors.primary,
    foregroundIndicatorStrokeWidth: Float = 50f,
//    indicatorStrokeCap: StrokeCap = StrokeCap.Round,
    bigTextFontSize: TextUnit = MaterialTheme.typography.h4.fontSize,
    bigTextColor: Color = MaterialTheme.colors.onSurface,
    bigTextSuffix: String = "",
    smallText: String = "Remaining",
    smallTextFontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    smallTextColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f)
) {
//    var allowedIndicatorValue by remember {mutableStateOf(maxIndicatorValue) }

    val allowedIndicatorValue:Float = if (indicatorValue <= maxIndicatorValue) {
        indicatorValue.toFloat()
    } else {
        maxIndicatorValue.toFloat()
    }

    val allowedProjectionIndicatorValue:Float =
        if ((allowedIndicatorValue + projectionIndicatorValue) <= maxIndicatorValue) {
        projectionIndicatorValue.toFloat()
    } else {
            maxIndicatorValue - allowedIndicatorValue
    }

//    var animatedIndicatorValue by remember { mutableStateOf(0f) }
//    LaunchedEffect(key1 = allowedIndicatorValue) {
//        animatedIndicatorValue = allowedIndicatorValue.toFloat()
//    }
// Calculates the percentage value of the amount completed on the progress bar
    val percentage = (allowedIndicatorValue / maxIndicatorValue) * 100
    val projectionPercentage = (allowedProjectionIndicatorValue / maxIndicatorValue) * 100
//gives
    val sweepAngle by animateFloatAsState(
        //we are uisng 2.4 because maximum value of the progress should be
        // 240f(thats the angle we take)
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(1000)
    )

    //gives
    val sweepProjectionAngle by animateFloatAsState(
        //we are uisng 2.4 because maximum value of the progress should be
        // 240f(thats the angle we take)
        targetValue = (2.4 * projectionPercentage).toFloat(),
        animationSpec = tween(1000)
    )

    val receivedValue by animateIntAsState(
        targetValue = indicatorValue,
        animationSpec = tween(1000)
    )

    val animatedBigTextColor by animateColorAsState(
        targetValue = MaterialTheme.colors.onSurface.copy(alpha = 0.3f + 0.7f * (percentage/100)),
//        targetValue = if (allowedIndicatorValue == 0f)
//            MaterialTheme.colors.onSurface.copy(alpha = 0.3f)
//        else
//            bigTextColor,
        animationSpec = tween(1000)
    )

    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                //Progress bar will be small in size than the canvas hence we divide the size of canvas with 1.25f
                val componentSize = size / 1.25f

                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth,
//                    indicatorStokeCap = indicatorStrokeCap
                )
                foregroundIndicator(
                    startAngle = 150f + sweepAngle,
                    sweepAngle = sweepProjectionAngle,
                    componentSize = componentSize,
                    indicatorColor = projectionIndicatorColor.copy(alpha = 0.25f),
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth,
//                    indicatorStokeCap = indicatorStrokeCap
                )
                foregroundIndicator(
                    startAngle = 150f,
                    sweepAngle = sweepAngle,
                    componentSize = componentSize,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth,
//                    indicatorStokeCap = indicatorStrokeCap
                )
            }
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EmbeddedElements(
            bigText = receivedValue,
            bigTextFontSize = bigTextFontSize,
            bigTextColor = animatedBigTextColor,
            bigTextSuffix = bigTextSuffix,
            smallText = smallText,
            smallTextColor = smallTextColor,
            smallTextFontSize = smallTextFontSize,
            Steps = indicatorValue,
            TotalSteps = maxIndicatorValue
        )
    }
//    Column (verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
// Box(modifier = Modifier
//     .size(200.dp)
//     .background(color = Color.Blue)
//     .clip(RoundedCornerShape(20.dp))
//
// )
//
//    }
}
//the background of the progress bar shape

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    //use adroidx.compose.ui.graphics
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
//    indicatorStokeCap: StrokeCap
) {
    //Creating the arc(progress) for the background of the actual progress bar
    drawArc(
        size = componentSize,
        color = indicatorColor,
        //making the start point for the progress bar change based on angle
        startAngle = 150f,
        sweepAngle = 240f,
        //set to false so that the we can create
        // a gap below the progress so that it doesnt not connect to the center point of canvas
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        //to center the progress(background indicator) into the canvas
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )

}
// main colored indicator for progress bar
//fun DrawScope.foregroundIndicator(
//    startAngle: Float,
//    sweepAngle: Float,
//    componentSize: Size,
//    indicatorColor: Color,
//    indicatorStrokeWidth: Float,
////    indicatorStokeCap: StrokeCap
//) {
//    drawArc(
//        size = componentSize,
//        color = indicatorColor,
//        startAngle = startAngle,
//        //change back to "sweepAngle" to reset it to calculated measurement
//        sweepAngle = sweepAngle,
//        useCenter = false,
//        style = Stroke(
//            width = indicatorStrokeWidth,
//            cap = StrokeCap.Round
//        ),
//        topLeft = Offset(
//            x = (size.width - componentSize.width) / 2f,
//            y = (size.height - componentSize.height) / 2f
//        )
//    )
//}

fun DrawScope.foregroundIndicator(
    startAngle: Float,
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
//    indicatorStokeCap: StrokeCap
) {
    val outerRadius = (componentSize.width / 2f) + indicatorStrokeWidth -12f
    val innerRadius = (outerRadius - indicatorStrokeWidth) - 10f

    // draw outer circle
    drawCircle(
        color = LightOrange,
        center = Offset(size.width / 2f, size.height / 2f),
        radius = outerRadius,
        style = Stroke(
            width = indicatorStrokeWidth / 2f,
            cap = StrokeCap.Round
        ),
    )

    // draw inner arc
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )

    // draw inner circle
    drawCircle(
        color = LLightOrange,
        center = Offset(size.width / 2f, size.height / 2f),
        radius = innerRadius,
        style = Fill,
    )
}
@Composable
fun EmbeddedElements(
    bigText: Int,
    bigTextFontSize: TextUnit,
    bigTextColor: Color,
    bigTextSuffix: String,
    smallText: String,
    smallTextColor: Color,
    smallTextFontSize: TextUnit,
    Steps: Int,
    TotalSteps: Int

) {
// shows the steps
    Text(
        text = "Steps",
        color = Color.Black,
        fontSize = smallTextFontSize,
        textAlign = TextAlign.Center
    )
    Text(
        text = "$bigText${bigTextSuffix.take(2)}",
        color = bigTextColor,
        fontSize = bigTextFontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )

    Divider(modifier = Modifier
//        .height(5.dp)
        .width(80.dp)
        .clip(CustomShapes.round())
        .background(color = Color.Gray),
        thickness = 1.dp
    )


    Text(
        text = "$TotalSteps",
        fontSize = bigTextFontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = ButtonOrange
    )




}



@Composable
@Preview(showBackground = true)
fun CustomComponentPreview() {
    CustomComponent()
}
