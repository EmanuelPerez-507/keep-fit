package com.example.keepfit.Goals.View

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Feature(
    val id:Int,
    val title: String,
    val steps: String,
    @DrawableRes val iconId: Int,
    val lightColor:Color,
    val mediumColor:Color,
    val darkColor:Color
)
