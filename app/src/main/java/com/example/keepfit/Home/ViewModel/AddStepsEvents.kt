package com.example.keepfit.Home.ViewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.LinkedList

class AddStepsEvents{

    private val _stepsProposalEvent = MutableStateFlow<Int>(0)
    private val _stepsCommitEvent = MutableSharedFlow<Unit>()

    private val proposalActions:LinkedList<(Int)->Unit> = LinkedList<(Int)->Unit>()
    private val commitAction:LinkedList<()->Unit> = LinkedList<()->Unit>()

    fun subscribeTo(action:(Int)->Unit){
        proposalActions.add(action)
    }

    fun subscribeTo(action:()->Unit){
        commitAction.add(action)
    }

    fun initBroadcast(scope:CoroutineScope){
        scope.launch {
            _stepsProposalEvent.collect{proposal->
                proposalActions.forEach{action->
                    action.invoke(proposal)
                }
            }
        }
        scope.launch {
            _stepsCommitEvent.collect{unit->
                commitAction.forEach{action->
                    action.invoke()
                }
            }
        }
    }

    suspend fun proposeSteps(steps:Int){
        if(steps>=0){
            _stepsProposalEvent.emit(steps)
        }
    }

    suspend fun commitSteps(){
        _stepsCommitEvent.emit(Unit)
    }

}