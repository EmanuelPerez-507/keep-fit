package com.example.keepfit.Home.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.Start
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeVM: ViewModel() {
    private var _currentSteps:Int by mutableStateOf(0)
    private var _projectionSteps:Int by mutableStateOf(0)
    private var _currentCalories:Double by mutableStateOf(0.0)
    private var _currentDistance:Double by mutableStateOf(0.0)

    var currentSteps:Int
        get() = _currentSteps
        set(value:Int){
            _currentSteps = value
        }

    var projectionSteps:Int
        get() = _projectionSteps
        set(value:Int){
            _projectionSteps = value
        }

    var currentCalories:Double
        get() = _currentCalories
        set(value:Double){
            _currentCalories = value
        }

    var currentDistance:Double
        get() = _currentDistance
        set(value:Double){
            _currentDistance = value
        }


    fun commitSteps(){
        _currentSteps += _projectionSteps
        _projectionSteps = 0
    }

    fun calculateCalories(){
        currentCalories = currentSteps * 0.04
    }

    fun calculateDistance(){
        currentDistance = currentSteps * 0.000397727
    }

}