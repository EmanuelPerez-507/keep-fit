package com.example.keepfit.DataLayer

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.keepfit.DataLayer.Goals.Goal
import com.example.keepfit.DataLayer.Goals.Goals

@Database(entities = [Goal::class], version = 1)
abstract class KeepFitDB:RoomDatabase(){


    abstract fun Goals():Goals

}