package com.example.navoop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navoop.ui.screen.HomeScreen
import com.example.navoop.ui.screen.OnboardingScreen
import com.example.navoop.ui.screen.Screen2
import com.example.navoop.ui.screen.Screen3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen(navController) }
        composable("screen2") { Screen2(navController) }
        composable("onboarding") { OnboardingScreen(navController) }
        composable("screen3") { Screen3(navController) }
    }
}
