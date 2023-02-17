package com.example.keepfit.Goals.ViewModel.Create

import androidx.compose.ui.graphics.toArgb
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.Goals.ViewModel.Create.GoalCreateModel
import com.example.keepfit.Start
import com.example.keepfit.TemplateFunctionality.Expandable
import com.example.keepfit.TemplateFunctionality.ExpandableVM
import com.example.keepfit.ui.theme.GoalBacks
import java.lang.Integer.parseInt

class ExpandableGoalCreateModel(
    override val expandable: Expandable,
    override val mainVM: GoalCreateModel
) :ExpandableVM<GoalCreateModel>{

    fun expansionBtnClick(){
        if(this.expandable.expanded){
            this.mainVM.addGoal()
        }
        this.expandable.expanded = !this.expandable.expanded
    }

}