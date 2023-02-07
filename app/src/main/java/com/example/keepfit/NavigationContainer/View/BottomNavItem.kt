package com.example.keepfit.NavigationContainer.View

import androidx.annotation.Nullable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import com.example.keepfit.ui.theme.CustomShapes

@Composable
fun BottomNavItem(title:String, onClick: () -> Unit = {}) {
    Button(
        elevation = null,
        modifier = Modifier.clip(shape = CustomShapes.noRound()).fillMaxHeight(),
        onClick = onClick
    ) {
        Text(text = title)
    }
}