package com.example.keepfit.DataLayer.Goals

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Goal")
data class Goal(
    @PrimaryKey(autoGenerate = true) var id:Int,
    @ColumnInfo(name = "name") var name:String,
    @ColumnInfo(name = "steps") var steps:Int,
    @ColumnInfo(name = "color") var color:Int)