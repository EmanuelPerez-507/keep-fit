import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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
import com.example.keepfit.ui.theme.*


@SuppressLint("UnusedTransitionTargetStateParameter")
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
    backgroundIndicatorStrokeWidth: Float = 65f,
     projectionIndicatorColor: Color = MaterialTheme.colors.secondary,
    foregroundIndicatorColor: Color = darkBlue,
    foregroundIndicatorStrokeWidth: Float = 65f,
//    indicatorStrokeCap: StrokeCap = StrokeCap.Round,
    bigTextFontSize: TextUnit = MaterialTheme.typography.h4.fontSize,
     percentageFont: TextUnit = MaterialTheme.typography.h3.fontSize,

     bigTextColor: Color = MaterialTheme.colors.onSurface,
    bigTextSuffix: String = "",
    smallText: String = "Remaining",
    smallTextFontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    smallTextColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f)
) {

    //validation to check it doesn't goes a 100%

    val allowedIndicatorValue:Float =
        if (indicatorValue <= maxIndicatorValue) {
        indicatorValue.toFloat()
    } else {
        maxIndicatorValue.toFloat()
    }

    val allowedProjectionIndicatorValue:Float =
        if ((allowedIndicatorValue + projectionIndicatorValue) <= maxIndicatorValue) {
        projectionIndicatorValue.toFloat()
    } else {
            (maxIndicatorValue.toFloat() - allowedIndicatorValue.toFloat()).toFloat()
    }

// Calculates the percentage value of the amount completed on the progress bar
    val percentage = (allowedIndicatorValue / maxIndicatorValue) * 100
    val projectionPercentage = (allowedProjectionIndicatorValue / maxIndicatorValue) * 100

    //animations

    val transitionDuration = 1000

    val masterTransition:Transition<Int> = updateTransition(targetState = indicatorValue,
        label = "Progress bar transition")

    val showPercentage:Float by masterTransition.animateFloat(
        label = "actual progress",
        transitionSpec = {tween(transitionDuration)}
    ){
        percentage
    }

    val rawShowPercentage:Float by masterTransition.animateFloat(
        label = "Text percentage transition",
        transitionSpec = {tween(transitionDuration)}
    ){rawSteps ->
        rawSteps.toFloat()/maxIndicatorValue.toFloat() * 100
    }

    val showProjectionPercentage by masterTransition.animateFloat(
        label = "progress projection",
        transitionSpec = {tween(transitionDuration)}
    ){
        projectionPercentage
    }

    println(showProjectionPercentage)

    val showSteps by masterTransition.animateInt(
        label = "int steps animation",
        transitionSpec = {tween(transitionDuration)}
    ){rawSteps->
        rawSteps
    }

    val animatedBigTextColor by masterTransition.animateColor(
        label = "steps color animation",
        transitionSpec = {tween(transitionDuration)}
    ){
        MaterialTheme.colors.onSurface.copy(alpha = 0.3f + 0.7f * (percentage/100))
    }

    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                //Progress bar will be small in size than the canvas hence we divide the size of canvas with 1.25f
                val componentSize = size
                innerOutterCircle(
                    componentSize = componentSize,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth,
                )
                //the back
                indicator(
                    startAngle = 150f,
                    sweepAngle = 240f,
                    percentage = 100f,
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth,
                )
                //the preview
                indicator(
                    startAngle = 150f + (240f * (showPercentage / 100)),
                    sweepAngle = 240f,
                    percentage = showProjectionPercentage,
                    componentSize = componentSize,
                    indicatorColor = projectionIndicatorColor.copy(alpha = 0.25f),
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth,
                )
                //the actual loading bar
                indicator(
                    startAngle = 150f,
                    sweepAngle = 240f,
                    percentage = showPercentage,
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
            bigText = showSteps,
            bigTextFontSize = bigTextFontSize,
            bigTextColor = animatedBigTextColor,
            bigTextSuffix = bigTextSuffix,
            smallText = smallText,
            smallTextColor = smallTextColor,
            smallTextFontSize = smallTextFontSize,
            Steps = indicatorValue,
            TotalSteps = maxIndicatorValue,
            StepPercentage = rawShowPercentage,
            percentageFont = percentageFont
        )
    }
}
//the background of the progress bar shape


fun DrawScope.innerOutterCircle(
    componentSize: Size,
    indicatorStrokeWidth: Float,
){
//    val innerRadius = (outerRadius - indicatorStrokeWidth) - 10f
    val innerRadius = (componentSize.width/2f) - (indicatorStrokeWidth*1.5f)

    // draw inner circle
    drawCircle(
        color = LLightOrange,
        center = Offset(size.width / 2f, size.height / 2f),
        radius = innerRadius,
        style = Fill,
    )

    val outerRadius = (componentSize.width / 2f) -  indicatorStrokeWidth / 4f

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


}

fun DrawScope.indicator(
    startAngle: Float,
    sweepAngle: Float,
    percentage: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
) {

    val reduceFactor = indicatorStrokeWidth * 2f

    // draw inner arc
    drawArc(
        size = Size(
            width = componentSize.width - reduceFactor,
            height = componentSize.height - reduceFactor
        )
        ,color = indicatorColor,
        startAngle = startAngle,
        sweepAngle = sweepAngle * (percentage/100),
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = reduceFactor / 2f,
            y = reduceFactor / 2f
        )
    )

}
@Composable
fun EmbeddedElements(
    bigText: Int,
    bigTextFontSize: TextUnit,
    percentageFont: TextUnit,
    bigTextColor: Color,
    bigTextSuffix: String,
    smallText: String,
    smallTextColor: Color,
    smallTextFontSize: TextUnit,
    Steps: Int,
    TotalSteps: Int,
    StepPercentage: Float
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

    Text(
        modifier = Modifier.padding(start = 10.dp),
        text = "${String.format("%.0f", StepPercentage)}%",
        fontSize = percentageFont,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = darkBlue
    )




}



@Composable
@Preview(showBackground = true)
fun CustomComponentPreview() {
    CustomComponent()
}
