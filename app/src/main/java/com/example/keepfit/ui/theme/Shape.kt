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

object CustomShapes {

    fun noRound(): RoundedCornerShape{
        return RoundedCornerShape(0.dp)
    }

    fun round(): RoundedCornerShape{
        return RoundedCornerShape(50)
    }

    object onlyTop {

        fun small(): RoundedCornerShape{
            return RoundedCornerShape(topStart = small, topEnd = small)
        }
        fun medium(): RoundedCornerShape {
            return RoundedCornerShape(topStart = medium, topEnd = medium)
        }
        fun large(): RoundedCornerShape{
            return RoundedCornerShape(topStart = large, topEnd = large)
        }

    }

}