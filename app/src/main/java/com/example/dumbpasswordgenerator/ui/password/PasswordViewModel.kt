package com.example.dumbpasswordgenerator.ui.password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PasswordViewModel: ViewModel() {
    var password by mutableStateOf("")
    var feedback by mutableStateOf("Feedback")
}
