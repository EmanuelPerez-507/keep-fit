package com.example.keepfit.DataLayer.Goals

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Goals {

    @Query("SELECT * FROM Goal")
    fun getAll():List<Goal>

    @Insert
    fun create(newGoal: Goal):Unit

}