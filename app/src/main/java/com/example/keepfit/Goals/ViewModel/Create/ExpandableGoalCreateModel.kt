package com.example.keepfit.Goals.ViewModel.Create

import com.example.keepfit.Functionality.Template.ExpandState
import com.example.keepfit.Functionality.Template.Expandable

class ExpandableGoalCreateModel:GoalCreateModel(), Expandable {

    private val expandedState: ExpandState = ExpandState()
    fun expansionBtnClick(){
        if(this.expandedState.expanded){
            addGoal()
        }
        this.expandedState.expanded = !this.expandedState.expanded
    }

    override fun expandable(): ExpandState {
        return expandedState
    }

}