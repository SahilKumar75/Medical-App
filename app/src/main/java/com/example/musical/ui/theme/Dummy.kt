package com.example.musical.ui.theme

import androidx.annotation.DrawableRes
import com.example.musical.R

data class Lib(@DrawableRes val icon: Int, val name:String)

val libraries = listOf<Lib>(
    Lib(R.drawable.baseline_add_call_24, "Call your Doc"),
    Lib(R.drawable.baseline_message_24,"Message your Doc"),
    Lib(R.drawable.baseline_directions_car_24,"Ambulance"), Lib(
        R.drawable.baseline_medication_24,"Medicine"
    ),Lib(R.drawable.baseline_feedback_24,"Feedback")
)