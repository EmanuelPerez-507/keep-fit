package com.example.keepfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.DataLayer.KeepFitDB
import com.example.keepfit.Goals.ViewModel.Create.ExpandableGoalCreateModel
import com.example.keepfit.Goals.ViewModel.Show.GoalScreenModel
import com.example.keepfit.Goals.ViewModel.Show.HomeScreenModel
import com.example.keepfit.History.ViewModel.HistoryScreenViewModel
import com.example.keepfit.Home.View.StepsTable
import com.example.keepfit.Home.ViewModel.ExpandableAddStepsVM
import com.example.keepfit.Home.ViewModel.HomeVM
import com.example.keepfit.NavigationContainer.NavigationContainer
import com.example.keepfit.NavigationContainer.ViewModel.ExpandableSettingsViewModel
import com.example.keepfit.ui.theme.GoalBacks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Start : ComponentActivity() {

    companion object{

        val dbThread:ExecutorService = Executors.newSingleThreadExecutor()
        var database:KeepFitDB? = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        database = Room.databaseBuilder(
            applicationContext, KeepFitDB::class.java, "goal-history-db"
        ).build()

        val initialGoals = listOf<Goal>(
             Goal(0, "Blue", 3000, GoalBacks.Blue.toArgb()),
             Goal(0, "Green", 3000, GoalBacks.Green.toArgb()),
             Goal(0, "Yellow", 3000, GoalBacks.Yellow.toArgb()),
             Goal(0, "Red", 3000, GoalBacks.Red.toArgb()),
             Goal(0, "Purple", 3000, GoalBacks.Pink.toArgb()),
             Goal(0, "Pink", 3000, GoalBacks.Purple.toArgb())
        )

        //goals
        val goalsScreenView: GoalScreenModel by viewModels()
        //Create goal dialog
        val expandableCreateGoalVM:ExpandableGoalCreateModel by viewModels()

        //home
        val homeVM:HomeVM by viewModels()
        //add steps dialog
        val expandableAddStepsVM:ExpandableAddStepsVM by viewModels()
        //
        val homeScreenView: HomeScreenModel by viewModels()

        val historyScreenView: HistoryScreenViewModel by viewModels()

        expandableAddStepsVM.eventsBus.initBroadcast(lifecycleScope)
        //makeHomeVM aware of events in addStepsVM
        expandableAddStepsVM.eventsBus.subscribeTo(homeVM::commitSteps)
        expandableAddStepsVM.eventsBus.subscribeTo(homeVM::projectionSteps::set)
        expandableAddStepsVM.eventsBus.subscribeTo(homeVM::calculateCalories)
        expandableAddStepsVM.eventsBus.subscribeTo(homeVM::calculateDistance)

        //settings button (temporary)
        val settingsExpandable:ExpandableSettingsViewModel by viewModels()

        lifecycleScope.launch(Dispatchers.Default){
//            for (initialGoal in initialGoals) {
//                database!!.Goals().create(initialGoal)
//            }
            goalsScreenView.init()
            homeScreenView.init()
            historyScreenView.init()
        }

        setContent {
            NavigationContainer(
                goalsScreenView,
                expandableCreateGoalVM,

                homeVM,
                expandableAddStepsVM,

                historyScreenView,

                settingsExpandable
            )
        }

    }
}