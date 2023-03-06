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

    suspend fun init() {

        val stepsList: Flow<List<Step>> = Start.database!!.Steps().getAll()
        val goalsList: Flow<List<Goal>> = Start.database!!.Goals().getAll()

        stepsList.collect{stepsList->
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
}
