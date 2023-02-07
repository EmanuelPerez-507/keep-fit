package com.example.keepfit.NavigationContainer.View

import com.example.keepfit.R

class BottomNavItems(val label:String, val icon: Int, val route:String = "") {

    companion object{

        val items = listOf(

        BottomNavItems("Home", R.drawable.home_icon, "Home"),
        BottomNavItems("Goals", R.drawable.home_icon, "Goals"),
        BottomNavItems("History", R.drawable.home_icon)

        )

    }

}