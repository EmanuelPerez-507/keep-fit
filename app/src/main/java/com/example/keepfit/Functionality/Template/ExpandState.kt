package com.example.keepfit.Functionality.Template

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect

class ExpandState {

    var _expanded:Boolean by mutableStateOf(false)

    var expandedBox: Rect? = null

    var expanded:Boolean
        set(value){
            _expanded = value
            if(!value){ expandedBox = null }
        }
        get() = _expanded

}