package com.example.keepfit.DataLayer

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.DataLayer.Goals.Goals
import com.example.keepfit.DataLayer.Steps.Step
import com.example.keepfit.DataLayer.Steps.Steps


@Database(entities = [Goal::class, Step::class], version = 1)
abstract class KeepFitDB:RoomDatabase(){
    abstract fun Goals():Goals
    abstract fun Steps():Steps
}
