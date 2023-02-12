package com.example.keepfit.Controls

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.example.keepfit.ui.theme.CustomShapes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SegmentedButton(vararg segments:String, selected:Int = 0) {

    var selectedIndex:Int by remember { mutableStateOf(selected) }

    val lastElementIndex = segments.size - 1

    Row(){

        segments.mapIndexed{ index, title ->

            OutlinedButton(
                modifier = Modifier
                    .width(45.dp)
                    .height(30.dp),
                onClick = {selectedIndex = index},
                contentPadding = PaddingValues(0.dp),
                shape = when(index){
                    0-> CustomShapes.onlyLeft.small
                    lastElementIndex -> CustomShapes.onlyRight.small
                    else -> RectangleShape
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = when(index){
                    selectedIndex -> MaterialTheme.colors.primary
                    else -> Color.Transparent
                })
                ) {

                Text(title, style = MaterialTheme.typography.caption)

            }

        }

    }
}