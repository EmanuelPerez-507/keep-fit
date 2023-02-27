package com.example.keepfit.Goals.ViewModel.Create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.Start
import com.example.keepfit.ui.theme.GoalBacks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Integer.parseInt

open class GoalCreateModel:ViewModel() {

    private var _newGoalName:String by mutableStateOf("")
    private var _newGoalSteps:String by mutableStateOf("")
    private var _newGoalColor:Color by mutableStateOf(GoalBacks.Green)

    var goalName:String
        set(value){ _newGoalName = value }
        get() = _newGoalName

    var goalSteps:String
        set(value){ _newGoalSteps = value }
        get() = _newGoalSteps

    var goalColor:Color
        set(value){ _newGoalColor = value }
        get() = _newGoalColor

    fun addGoal(){
        val newGoal: Goal = Goal(
            id = 0,
            name = goalName,
            steps = parseInt(goalSteps),
            color = goalColor.toArgb()
        )
        viewModelScope.launch(Dispatchers.Default){
            Start.database!!.Goals().create(newGoal)
        }
    }

}