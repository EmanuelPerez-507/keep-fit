package com.example.keepfit.Home.ViewModel

import com.example.keepfit.TemplateFunctionality.ExpandState
import com.example.keepfit.TemplateFunctionality.Expandable

class ExpandableAddStepsVM:AddStepsVM(),Expandable{

    private val expandableState:ExpandState = ExpandState()

    fun onExpandButtonClick(){
        this.expandableState.expanded = !this.expandableState.expanded
    }

    override fun expandable(): ExpandState {
        return expandableState
    }

}