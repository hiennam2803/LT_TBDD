package com.example.forgetpass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.forgetpass.viewmodel.ForgetPasswordViewModel
import com.example.forgetpass.ui.screen.NavGraph




class MainActivity : ComponentActivity() {
    private val viewModel: ForgetPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavGraph(navController, viewModel)
        }
    }
}
