package com.example.navoop.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.navoop.R
import com.example.navoop.data.model.OnboardingPage
import com.example.navoop.ui.component.Screen3Item

@Composable
fun Screen3(navController: NavController) {
    val firstPage = OnboardingPage(
        title = "IReminder Notification",
        description = "The advantage of this application is that it also provides reminders for you so you don’t forget to keep doing your assignments and within the time you have set.",
        imageRes = R.drawable.img3
    )

    Screen3Item(
        navController = NavController,
        page = firstPage,
        onNextClick = { navController.navigate("home") },
        onBackClick = { navController.popBackStack() },
        onSkipClick = { navController.navigate("home") }
    )
}
