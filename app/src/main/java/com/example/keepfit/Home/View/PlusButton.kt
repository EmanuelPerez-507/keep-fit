package com.example.keepfit.Home.View

import android.widget.EditText
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.keepfit.Controls.ExpandableFAB
import com.example.keepfit.Home.ViewModel.ExpandableAddStepsVM
import com.example.keepfit.R
import com.example.keepfit.Settings.View.SettingsScreen
import com.example.keepfit.TemplateFunctionality.Expandable
import com.example.keepfit.ui.theme.CancelRed
import com.example.keepfit.ui.theme.medium

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PlusButton(
    alignment:Modifier,
    state: ExpandableAddStepsVM
) {

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val close:(Boolean)->Unit = fun (commit:Boolean){
        keyboardController!!.hide()
        focusManager.clearFocus(true)
        if(commit) state.commitSteps() else state.augmentSteps = ""
    }

    ExpandableFAB(
        compressedOffsetH = (-15).dp,
        expandedOffsetH = (-20).dp,
//        expandedOffsetV = (-200).dp,
        expandedButtonColor = CancelRed,
        FABColor = Color.DarkGray,
        height = 55.dp,
        width = 300.dp,
        iconId = R.drawable.plus_round_icon,
        iconDescriptor = "Plus icon",
        alignment = alignment,
        state = state.expandable(),
        anchor = Alignment.BottomEnd,
        rotation = -135F,
        beforeClose = {close(false)},
        beforeOpen = {focusRequester.requestFocus()},
    ){

        TextField(
            singleLine = true,
            modifier = Modifier
                .width(235.dp)
                .focusRequester(focusRequester),
            value = state.augmentSteps,
            onValueChange = state::augmentSteps::set,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(
                onGo = {
                    close(true)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White
            ),
            shape = RectangleShape
        )

    }

}