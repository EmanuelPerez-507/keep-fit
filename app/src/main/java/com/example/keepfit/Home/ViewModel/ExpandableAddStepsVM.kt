package com.example.keepfit.Home.ViewModel

import com.example.keepfit.TemplateFunctionality.ExpandState
import com.example.keepfit.TemplateFunctionality.Expandable

class ExpandableAddStepsVM:AddStepsVM(),Expandable{

    private val expandableState:ExpandState = ExpandState()

    override fun expandable(): ExpandState {
        return expandableState
    }

    override fun commitSteps(){
        expandableState.expanded = false
        super.commitSteps()
    }

}