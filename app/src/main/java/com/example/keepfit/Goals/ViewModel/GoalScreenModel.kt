package com.example.keepfit.Goals.ViewModel

<<<<<<< Updated upstream
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.DataLayer.Goals.Goals
import com.example.keepfit.DataLayer.KeepFitDB
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GoalScreenModel: ViewModel(){

    private var _goalsList:List<Goal> by mutableStateOf(emptyList())

    val goalsList:List<Goal>
        get() = _goalsList

    fun init(goalsDb:KeepFitDB){
        _goalsList = goalsDb.Goals().getAll()
    }
=======
class GoalScreenModel {


>>>>>>> Stashed changes

}