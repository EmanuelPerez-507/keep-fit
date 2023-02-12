package com.example.keepfit.Controls

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Composable
fun SegmentedButton(vararg segments:String) {
    Row(){

        segments.map { title ->
            OutlinedButton(onClick = {}) {
            Text(title, style = MaterialTheme.typography.caption)
        }

        }

    }
}