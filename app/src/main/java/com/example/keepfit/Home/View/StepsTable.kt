package com.example.keepfit.Home.View

data class StepsTable(
    val id: Int,
    val steps: Int,
    val dateAdded: Long = System.currentTimeMillis(),
    val beenDeleted: Boolean
)