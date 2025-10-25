package com.example.forgetpass.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*

class ForgetPasswordViewModel : ViewModel() {
    var email by mutableStateOf("")
    var code by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
}
