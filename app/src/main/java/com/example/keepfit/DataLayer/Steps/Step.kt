package com.example.keepfit.DataLayer.Steps

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Step")
data class Step(
    @PrimaryKey(autoGenerate = true) var id:Int,
    @ColumnInfo(name = "Steps Added") var name:String,
    @ColumnInfo(name = "Step Number") var steps:Int,
    @ColumnInfo(name = "Date Added") var date:String,
    @ColumnInfo(name = "Been Deleted?") var delete:Boolean,
)