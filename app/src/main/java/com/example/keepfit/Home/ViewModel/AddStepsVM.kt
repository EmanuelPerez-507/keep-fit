package com.example.keepfit.Home.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.DataLayer.Steps.Step
import com.example.keepfit.Home.View.PassThrough
import com.example.keepfit.Start
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Integer.parseInt
import java.text.SimpleDateFormat
import java.util.*

open class AddStepsVM:ViewModel() {

    val eventsBus:AddStepsEvents = AddStepsEvents()
    private var _augmentSteps:String by mutableStateOf("")

    var augmentSteps:String
        get() = _augmentSteps
        set(value:String){
            if(value.length>0){
                try{
                    parseInt(value)
                    _augmentSteps = value
                    proposeSteps()
                }catch (ex:Exception){

                }
            }else{
                _augmentSteps = value
                proposeSteps()
            }
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
        addStepsDB(_augmentSteps)
        _augmentSteps = ""
        viewModelScope.launch {
            eventsBus.commitSteps()
            println("Passed All Steps!")
        }
    }

    fun addStepsDB(Pass:String){
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        val current = formatter.format(date)

        var passedSteps = Pass.toInt()
        println(passedSteps)

        val newStepEntry: Step = Step(
            id = 0,
            name = "Step Entry",
            steps = passedSteps,
            date = current,
            delete = false
        )

        viewModelScope.launch(Dispatchers.Default){
            Start.database!!.Steps().create(newStepEntry)
            println(current)
            println("Successfully added $passedSteps to Database!")
        }
    }

}