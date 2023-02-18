package com.example.keepfit.DataLayer.Goals

import androidx.compose.runtime.State
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface Goals {

    @Query("SELECT * FROM Goal")
    fun getAll(): Flow<List<Goal>>

    @Insert
    fun create(newGoal: Goal):Unit

    @Update
    fun set(newGoal: Goal):Unit

}