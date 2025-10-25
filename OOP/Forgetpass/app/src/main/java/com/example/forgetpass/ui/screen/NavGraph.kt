package com.example.forgetpass.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.forgetpass.viewmodel.ForgetPasswordViewModel

@Composable
fun NavGraph(navController: NavHostController, viewModel: ForgetPasswordViewModel) {
    NavHost(navController = navController, startDestination = "forget") {
        composable("forget") {
            ForgetPasswordScreen(viewModel, onNext = { navController.navigate("verify") })
        }
        composable("verify") {
            VerifyCodeScreen(
                viewModel,
                onNext = { navController.navigate("create") },
                onBack = { navController.popBackStack() }
            )
        }
        composable("create") {
            CreateNewPasswordScreen(
                viewModel,
                onNext = { navController.navigate("confirm") },
                onBack = { navController.popBackStack() }
            )
        }
        composable("confirm") {
            ConfirmScreen(viewModel, onBack = { navController.popBackStack("forget", inclusive = false) })
        }
    }
}
