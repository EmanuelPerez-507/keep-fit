package com.example.keepfit.TemplateFunctionality

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.lifecycle.ViewModel

class Expandable: ViewModel(){

    var _expanded:Boolean by mutableStateOf(false)

    var expandedBox:Rect? = null

    var expanded:Boolean
        set(value){
            _expanded = value
            if(!value){ expandedBox = null }
        }
        get() = _expanded

}