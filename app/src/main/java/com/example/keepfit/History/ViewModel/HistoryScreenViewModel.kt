package com.example.keepfit.History.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.keepfit.DataLayer.Steps.Step
import com.example.keepfit.Home.View.StepsTable
import com.example.keepfit.Start
import kotlinx.coroutines.flow.Flow

class HistoryScreenViewModel: ViewModel() {
    private var _stepsList: List<StepsTable> by mutableStateOf(emptyList())

    val stepsList: List<StepsTable>
        get() = _stepsList

    suspend fun init() {
        val stepsList: Flow<List<Step>> = Start.database!!.Steps().getAll()
        stepsList.collect{stepsList->
            _stepsList = stepsList.map{step->
                StepsTable(
                    id = step.id,
                    title = step.name,
                    steps = step.steps,
                    dateAdded = step.date,
                    beenDeleted = step.delete
                )
            }
        }
    }
}
