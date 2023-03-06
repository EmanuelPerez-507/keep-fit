package com.example.keepfit.Home.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.Start
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import java.util.*

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
        val calStart: Calendar = GregorianCalendar()
        calStart.setTime(Date())
        calStart.set(Calendar.HOUR_OF_DAY, 0)
        calStart.set(Calendar.MINUTE, 0)
        calStart.set(Calendar.SECOND, 0)
        val midnightYesterday: Long = calStart.timeInMillis

        val calEnd: Calendar = GregorianCalendar()
        calEnd.setTime(Date())
        calEnd.set(Calendar.DAY_OF_YEAR, calEnd.get(Calendar.DAY_OF_YEAR) + 1)
        calEnd.set(Calendar.HOUR_OF_DAY, 0)
        calEnd.set(Calendar.MINUTE, 0)
        calEnd.set(Calendar.SECOND, 0)
        val midnightTonight: Long = calEnd.timeInMillis
        println("$midnightYesterday, $midnightTonight")
            _currentSteps = Start.database!!.Steps().getAllSteps(midnightYesterday, midnightTonight)
            currentCalories = currentSteps * 0.04
            currentDistance = currentSteps * 0.000397727

    }

    fun commitSteps(){
        _currentSteps += _projectionSteps
        _projectionSteps = 0
    }

    fun calculateCalories(){

    }

    fun calculateDistance(){
    }

    fun newSelectedGoal(newSelGoalId:Int){
        _selectedGoalId = newSelGoalId
        this.resetSelectedGoal()
    }

}