package com.example.keepfit.Home.View

import java.util.Date

data class StepsTable (
    val id: Int,
    val title: String,
    val steps: Int,
    val dateAdded: String,
    val beenDeleted: Boolean
)