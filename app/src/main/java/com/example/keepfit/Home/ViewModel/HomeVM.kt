package com.example.keepfit.Home.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeVM: ViewModel() {

    private var _currentSteps:Int by mutableStateOf(0)
    private var _projectionSteps:Int by mutableStateOf(0)

    var currentSteps:Int
        get() = _currentSteps
        set(value:Int){
            _currentSteps = value
        }

    var projectionSteps:Int
        get() = _projectionSteps
        set(value:Int){
            println(value)
            _projectionSteps = _currentSteps + value
        }

    fun commitSteps(){
        _currentSteps = _projectionSteps
        _projectionSteps = 0
    }

}