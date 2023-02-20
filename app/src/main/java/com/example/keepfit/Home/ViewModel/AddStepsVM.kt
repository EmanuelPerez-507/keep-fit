package com.example.keepfit.Home.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Integer.parseInt

open class AddStepsVM:ViewModel() {

    val eventsBus:AddStepsEvents = AddStepsEvents()
    private var _augmentSteps:String by mutableStateOf("")

    var augmentSteps:String
        get() = _augmentSteps
        set(value:String){
            _augmentSteps = value
            proposeSteps()
        }

    open fun proposeSteps(){
        var augmentedSteps:Int = 0
        try{
            augmentedSteps = parseInt(_augmentSteps)
        }catch (ex:Exception){

        }finally {
            viewModelScope.launch {
                eventsBus.proposeSteps(augmentedSteps)
            }
        }
    }

    open fun commitSteps(){
        _augmentSteps = ""
        viewModelScope.launch {
            eventsBus.commitSteps()
        }
    }

}