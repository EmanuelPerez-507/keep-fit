package com.example.keepfit.Home.ViewModel

import com.example.keepfit.Functionality.Template.ExpandState
import com.example.keepfit.Functionality.Template.Expandable

class ExpandableAddStepsVM:AddStepsVM(), Expandable {

    private val expandableState: ExpandState = ExpandState()

    override fun expandable(): ExpandState {
        return expandableState
    }

    override fun commitSteps(){
        expandableState.expanded = false
        super.commitSteps()
    }

}