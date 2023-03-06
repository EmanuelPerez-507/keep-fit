package com.example.keepfit.Home.ViewModel

import android.database.Cursor
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.DataLayer.KeepFitDB
import com.example.keepfit.DataLayer.Steps.Steps
import com.example.keepfit.Start
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeVM: ViewModel() {

    private var _selectedGoalId:Int by mutableStateOf(-1)
    private var _selectedGoal: Goal? by mutableStateOf(null)
    private var _selectedGoalRetrieveJob: Job? = null

    private var _currentSteps:Int by mutableStateOf(0)
    private var _projectionSteps:Int by mutableStateOf(0)
    private var _currentCalories:Double by mutableStateOf(0.0)
    private var _currentDistance:Double by mutableStateOf(0.0)

    fun resetSelectedGoal(){
        _selectedGoalRetrieveJob?.cancel()
        val selectedGoalUpdatesFlow:Flow<Goal> = Start.database!!.Goals().getById(_selectedGoalId)
        _selectedGoalRetrieveJob = viewModelScope.launch {
            selectedGoalUpdatesFlow.collect(){newGoalState->
                _selectedGoal = newGoalState
            }
        }
    }

    val selectedGoal:Goal?
    get(){return _selectedGoal}


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


    suspend fun init(){

            _currentSteps = Start.database!!.Steps().getAllSteps()
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

    fun newSelectedGoal(newSelGoalId:Int){
        _selectedGoalId = newSelGoalId
        this.resetSelectedGoal()
    }

}