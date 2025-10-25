package com.example.navoop.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.navoop.R
import com.example.navoop.data.model.OnboardingPage
import com.example.navoop.ui.component.Screen2Item

@Composable
fun Screen2(navController: NavController) {
    val firstPage = OnboardingPage(
        title = "Increase Work Effectiveness",
        description = "Time management and the determination of more important tasks will give your job statistics better and always improve.",
        imageRes = R.drawable.img1
    )

    Screen2Item(
        navController = NavController,
        page = firstPage,
        onNextClick = { navController.navigate("onboarding") },
        onBackClick = { navController.popBackStack() },
        onSkipClick = { navController.navigate("home") }
    )
}
