package com.example.keepfit.NavigationContainer.ViewModel

import com.example.keepfit.TemplateFunctionality.ExpandState
import com.example.keepfit.TemplateFunctionality.Expandable

class ExpandableSettingsViewModel: SettingsViewModel(),Expandable{

    private val expandableState:ExpandState = ExpandState()

    override fun expandable(): ExpandState {
        return expandableState
    }


}