package com.example.keepfit.Goals.ViewModel.Show

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import androidx.lifecycle.ViewModel
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.Goals.View.Feature
import com.example.keepfit.Start
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class GoalScreenModel: ViewModel() {

    private var _goalsList: List<Feature> by mutableStateOf(emptyList())

    val goalsList: List<Feature>
        get() = _goalsList

    suspend fun init() {
        val goalsList: Flow<List<Goal>> = Start.database!!.Goals().getAll()
        goalsList.collect{goalsList->
            _goalsList = goalsList.map{goal->
                val color: Color = Color(
                    ColorUtils.blendARGB(goal.color, Color.White.toArgb(), 0.2F)
                )
                val mediumBlend = color.toArgb()
                val lightColor:Color = Color(
                    ColorUtils.blendARGB(mediumBlend, Color.White.toArgb(), 0.3F)
                )
                val darkColor:Color = Color(
                    ColorUtils.blendARGB(mediumBlend, Color.Black.toArgb(), 0.3F)
                )
                Feature(
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