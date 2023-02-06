package com.example.keepfit.ui.theme

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

val smallPercent = 15
val mediumPercent = 25
val largePercent = 35

val Shapes = Shapes(
    small = RoundedCornerShape(percent = smallPercent),
    medium = RoundedCornerShape(percent = mediumPercent),
    large = RoundedCornerShape(percent = largePercent)
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
            return RoundedCornerShape(topStartPercent = smallPercent, topEndPercent = smallPercent)
        }
        fun medium(): RoundedCornerShape {
            return RoundedCornerShape(topStartPercent = mediumPercent, topEndPercent = mediumPercent)
        }
        fun large(): RoundedCornerShape{
            return RoundedCornerShape(topStartPercent = largePercent, topEndPercent = largePercent)
        }

    }

}