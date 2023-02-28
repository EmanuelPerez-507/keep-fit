package com.example.keepfit.Utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

class Utils {

    class ColorMix{

        companion object {

            fun mediumLighten(color:Color):Color{
                return Color(
                    ColorUtils.blendARGB(
                        color.toArgb(),
                        Color.White.toArgb(),
                        0.2F)
                )
            }

            fun lighten(color: Color):Color {
                return Color(
                    ColorUtils.blendARGB(
                        color.toArgb(),
                        Color.White.toArgb(),
                        0.3F
                    )
                )
            }

            fun darken(color:Color):Color{
                return Color(
                    ColorUtils.blendARGB(
                        color.toArgb(),
                        Color.Black.toArgb(),
                        0.3F)
                )
            }

        }

    }

}