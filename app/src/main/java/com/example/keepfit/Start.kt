package com.example.keepfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.DataLayer.KeepFitDB
import com.example.keepfit.Goals.ViewModel.Create.ExpandableGoalCreateModel
import com.example.keepfit.Goals.ViewModel.Create.GoalCreateModel
import com.example.keepfit.Goals.ViewModel.Show.GoalScreenModel
import com.example.keepfit.Home.ViewModel.AddStepsEvents
import com.example.keepfit.Home.ViewModel.AddStepsVM
import com.example.keepfit.Home.ViewModel.ExpandableAddStepsVM
import com.example.keepfit.Home.ViewModel.HomeVM
import com.example.keepfit.NavigationContainer.NavigationContainer
import com.example.keepfit.NavigationContainer.ViewModel.ExpandableSettingsViewModel
import com.example.keepfit.TemplateFunctionality.Expandable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Modifier
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Start : ComponentActivity() {

    companion object{

        val dbThread:ExecutorService = Executors.newSingleThreadExecutor()
        var database:KeepFitDB? = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        database = Room.databaseBuilder(
            applicationContext, KeepFitDB::class.java, "goal-history-db"
        ).build()

        val initialGoals = listOf<Goal>(
             Goal(0, "Blue", 3000, Color(0xFF33BBFF).toArgb()),
             Goal(0, "Green", 3000, Color(0xFF00CC66).toArgb()),
             Goal(0, "Yellow", 3000, Color(0xFFFACA1E).toArgb()),
             Goal(0, "Red", 3000, Color(0xFFFF1A1A).toArgb()),
             Goal(0, "Purple", 3000, Color(0xFFbc89f5).toArgb()),
             Goal(0, "Pink", 3000, Color(0xFFD909C7).toArgb())
        )

        //goals
        val goalsScreenView: GoalScreenModel by viewModels()
        //Create goal dialog
        val expandableCreateGoalVM:ExpandableGoalCreateModel by viewModels()

        //home
        val homeVM:HomeVM by viewModels()
        //add steps dialog
        val expandableAddStepsVM:ExpandableAddStepsVM by viewModels()
        expandableAddStepsVM.eventsBus.initBroadcast(lifecycleScope)
        //makeHomeVM aware of events in addStepsVM
        expandableAddStepsVM.eventsBus.subscribeTo(homeVM::commitSteps)
        expandableAddStepsVM.eventsBus.subscribeTo(homeVM::projectionSteps::set)

        //settings button (temporary)
        val settingsExpandable:ExpandableSettingsViewModel by viewModels()

        lifecycleScope.launch(Dispatchers.Default){
            goalsScreenView.init()
        }

        setContent {
            NavigationContainer(
                goalsScreenView,
                expandableCreateGoalVM,

                homeVM,
                expandableAddStepsVM,

                settingsExpandable
            )
        }

    }
}