package com.example.keepfit.Goals.ViewModel.Show

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.LinkedList

class GoalListEvents {

    private val _selectedGoalIdEvent = MutableStateFlow(-1)

    private val _goalSelectedActions:LinkedList<(Int)->Unit> = LinkedList<(Int)->Unit>()

    fun subscribeTo(action: (Int)->Unit){
        _goalSelectedActions.add(action)
    }

    fun initBroadcast(scope: CoroutineScope){

        scope.launch {
            _selectedGoalIdEvent.collect(){newGoalId->
                _goalSelectedActions.forEach { action -> action.invoke(newGoalId) }
            }
        }

    }

    suspend fun selectGoal(newGoalId:Int){
        _selectedGoalIdEvent.emit(newGoalId)
    }

}