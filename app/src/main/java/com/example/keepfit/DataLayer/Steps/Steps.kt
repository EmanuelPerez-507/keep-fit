package com.example.keepfit.DataLayer.Steps

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.keepfit.DataLayer.Steps.Step
import kotlinx.coroutines.flow.Flow

@Dao
interface Steps {
    @Query("SELECT * FROM Step")
    fun getAll(): Flow<List<Step>>

    @Insert
    fun create(newSteps: Step):Unit

    @Update
    fun set(newSteps: Step):Unit
}