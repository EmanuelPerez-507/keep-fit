package com.example.keepfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Room
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.DataLayer.KeepFitDB
import com.example.keepfit.Goals.ViewModel.GoalScreenModel
import com.example.keepfit.NavigationContainer.NavigationContainer
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Start : ComponentActivity() {

    val dbThred:ExecutorService = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val goalHistoryDB = Room.databaseBuilder(
            applicationContext, KeepFitDB::class.java, "goal-history-db"
        ).build()

        val newGoal = Goal(0, "Goal1", 3000, Color.Blue.toArgb())

        val goalsScreenView:GoalScreenModel by viewModels()

        setContent {
            NavigationContainer(goalsScreenView)
        }

        dbThred.submit {
            goalHistoryDB.Goals().create(newGoal)
            goalsScreenView.init(goalHistoryDB)
        }

    }
}