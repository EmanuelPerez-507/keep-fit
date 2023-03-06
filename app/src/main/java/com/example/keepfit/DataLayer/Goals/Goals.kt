package com.example.keepfit.DataLayer.Goals

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Goals {

    @Query("SELECT * FROM Goal")
    fun getAll(): Flow<List<Goal>>

    @Query("SELECT * FROM Goal WHERE id=:id")
    fun getById(id:Int): Flow<Goal>

    @Insert
    fun create(newGoal: Goal):Unit

    @Update
    fun set(newGoal: Goal):Unit

}