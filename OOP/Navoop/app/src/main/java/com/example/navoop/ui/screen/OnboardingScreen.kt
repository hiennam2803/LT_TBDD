package com.example.navoop.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.navoop.R
import com.example.navoop.data.model.OnboardingPage
import com.example.navoop.ui.component.OnboardingItem

@Composable
fun OnboardingScreen(navController: NavController) {
    val firstPage = OnboardingPage(
        title = "Easy Time Management",
        description = "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first.",
        imageRes = R.drawable.img2
    )

    OnboardingItem(
        navController = NavController,
        page = firstPage,
        onNextClick = { navController.navigate("screen3") },
        onBackClick = { navController.popBackStack() },
        onSkipClick = { navController.navigate("home") }
    )
}
