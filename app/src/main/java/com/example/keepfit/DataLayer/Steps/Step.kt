package com.example.keepfit.DataLayer.Steps

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.System.currentTimeMillis
import java.util.Date

@Entity(tableName = "Step")
data class Step(
    @PrimaryKey(autoGenerate = true) var id:Int,
    @ColumnInfo(name = "Step_No") var steps:Int,
    @ColumnInfo(name = "Date_Add") var date:Long = currentTimeMillis(),
    @ColumnInfo(name = "Deleted?") var delete:Boolean,
)