package com.example.keepfit.SingletonPersistance

import kotlinx.serialization.Serializable

@Serializable
data class CacheData (
    var currentSteps:Int = 0,
    var currentSelectedGoal:Int = -1
        )