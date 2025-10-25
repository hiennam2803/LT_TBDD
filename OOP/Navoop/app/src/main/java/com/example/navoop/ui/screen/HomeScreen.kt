package com.example.navoop.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.navoop.R
import com.example.navoop.data.model.OnboardingPage
import com.example.navoop.ui.component.HomeScreen

@Composable
fun HomeScreen(navController: NavController) {
    val firstPage = OnboardingPage(
        title = "UTH SMARTTASK",
        description = "",
        imageRes = R.drawable.img
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate("screen2")
            }
    ) {
        HomeScreen(
            navController = navController,
            page = firstPage
        )
    }
}
