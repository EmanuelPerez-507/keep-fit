package com.example.keepfit.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val small = 15.dp
val medium = 25.dp
val large = 35.dp

val Shapes = Shapes(
    small = RoundedCornerShape(small),
    medium = RoundedCornerShape(medium),
    large = RoundedCornerShape(large)
)

enum class ShapeSide{
    LEFT,
    RIGHT
}

object CustomShapes {

    fun noRound(): RoundedCornerShape{
        return RoundedCornerShape(0.dp)
    }

    fun round(): RoundedCornerShape{
        return RoundedCornerShape(50)
    }

    val onlyTop:Shapes = Shapes(
        small = RoundedCornerShape(topStart = small, topEnd = small),
        medium = RoundedCornerShape(topStart = medium, topEnd = medium),
        large = RoundedCornerShape(topStart = large, topEnd = large)
    )

    val onlyBottom:Shapes = Shapes(
        small = RoundedCornerShape(bottomStart = small, bottomEnd = small),
        medium = RoundedCornerShape(bottomStart = medium, bottomEnd = medium),
        large = RoundedCornerShape(bottomStart = large, bottomEnd = large)
    )

    fun onlySide(side:ShapeSide = ShapeSide.LEFT):Shapes {
        return when(side){
            ShapeSide.LEFT->Shapes(
                small = RoundedCornerShape(topStart = small, bottomStart = small),
                medium = RoundedCornerShape(topStart = medium, bottomStart = medium),
                large = RoundedCornerShape(topStart = large, bottomStart = large)
            )
            ShapeSide.RIGHT->Shapes(
                small = RoundedCornerShape(bottomEnd = small, topEnd = small),
                medium = RoundedCornerShape(bottomEnd = medium, topEnd = medium),
                large = RoundedCornerShape(bottomEnd = large, topEnd = large)
            )
        }
    }

    val onlyLeft:Shapes = onlySide(ShapeSide.LEFT)
    val onlyRight:Shapes = onlySide(ShapeSide.RIGHT)

}