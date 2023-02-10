package com.example.keepfit.NavigationContainer.View

import com.example.keepfit.R

class Screen(val label:String, val icon: Int, val route:String = "") {

    companion object{

        val Home = Screen("Home", R.drawable.home_icon, "Home")
        val Goals = Screen("Goals", R.drawable.trophy_icon, "Goals")
        val History = Screen("History", R.drawable.history_icon, "History")

        val all = listOf(
            Home,Goals, History
        )

    }

}