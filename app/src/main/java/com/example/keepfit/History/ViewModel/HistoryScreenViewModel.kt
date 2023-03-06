package com.example.keepfit.History.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.DataLayer.Steps.Step
import com.example.keepfit.Goals.View.Feature
import com.example.keepfit.Home.View.StepsTable
import com.example.keepfit.Start
import com.example.keepfit.Utils.Utils
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat

class HistoryScreenViewModel: ViewModel() {
    private var _stepsList: List<StepsTable> by mutableStateOf(emptyList())
    private var _goalsList: List<Feature> by mutableStateOf(emptyList())

    val stepsList: List<StepsTable>
        get() = _stepsList

    val goalsList: List<Feature>
        get() = _goalsList

    suspend fun init1() {

        val stepsList: Flow<List<Step>> = Start.database!!.Steps().getAll()

        stepsList.collect{stepsList->
            println("${stepsList.size}")
            _stepsList = stepsList.map{step->
                StepsTable(
                    id = step.id,
                    steps = step.steps,
                    dateAdded = step.date,
                    beenDeleted = step.delete
                )
            }
        }
    }

    suspend fun init2(){
        val goalsList: Flow<List<Goal>> = Start.database!!.Goals().getAll()

        goalsList.collect{goalsList->
            _goalsList = goalsList.map{goal->
                val color: Color = Utils.ColorMix.mediumLighten(Color(goal.color))
                val lightColor:Color = Utils.ColorMix.lighten(color)
                val darkColor:Color = Utils.ColorMix.darken(color)
                Feature(
                    id = goal.id,
                    title = goal.name,
                    steps = goal.steps.toString(),
                    iconId = 0,
                    lightColor = lightColor,
                    mediumColor = color,
                    darkColor = darkColor
                )
            }
        }
    }
}
