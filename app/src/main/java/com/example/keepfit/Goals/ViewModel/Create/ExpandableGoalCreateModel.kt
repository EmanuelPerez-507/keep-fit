package com.example.keepfit.Goals.ViewModel.Create

import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.Goals.ViewModel.Create.GoalCreateModel
import com.example.keepfit.Start
import com.example.keepfit.TemplateFunctionality.ExpandState
import com.example.keepfit.TemplateFunctionality.Expandable
import com.example.keepfit.ui.theme.GoalBacks
import java.lang.Integer.parseInt

class ExpandableGoalCreateModel:GoalCreateModel(),Expandable{

    private val expandedState:ExpandState = ExpandState()
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