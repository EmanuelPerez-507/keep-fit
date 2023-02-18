package com.example.keepfit.TemplateFunctionality

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.lifecycle.ViewModel

interface Expandable{

    fun expandable():ExpandState

}