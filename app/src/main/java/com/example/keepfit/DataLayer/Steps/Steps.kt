package com.example.keepfit.DataLayer.Steps

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

@Dao
interface Steps {
    @Query("SELECT * FROM Step")
    fun getAll(): Flow<List<Step>>

    @Insert
    fun create(newSteps: Step):Unit

    @Update
    fun set(newSteps: Step):Unit

    @Query("SELECT SUM(Step_No) FROM Step WHERE Date_Add BETWEEN :startDate AND :endDate")
    fun getAllSteps(startDate:Long, endDate: Long): Int
}