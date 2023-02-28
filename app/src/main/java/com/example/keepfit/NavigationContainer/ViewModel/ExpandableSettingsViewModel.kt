package com.example.keepfit.NavigationContainer.ViewModel

import com.example.keepfit.Functionality.Template.ExpandState
import com.example.keepfit.Functionality.Template.Expandable

class ExpandableSettingsViewModel: SettingsViewModel(), Expandable {

    private val expandableState: ExpandState = ExpandState()

    override fun expandable(): ExpandState {
        return expandableState
    }


}