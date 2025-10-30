package com.example.signwithgg

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.signwithgg.model.UserModel
import com.example.signwithgg.ui.screen.ProfileScreen
import com.example.signwithgg.ui.screen.SignInScreen
import com.example.signwithgg.viewmodel.SignInViewModel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignWithGoogleApp()
        }
    }
}

@Composable
fun SignWithGoogleApp(viewModel: SignInViewModel = viewModel()) {
    val navController = rememberNavController()
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { _ -> }

    NavHost(
        navController = navController,
        startDestination = if (FirebaseAuth.getInstance().currentUser != null) "profile" else "sign_in"
    ) {
        // Màn hình đăng nhập
        composable("sign_in") {
            SignInScreen(
                onSignInClick = {
                    viewModel.signInWithGoogle(
                        context = context,
                        launcher = launcher,
                        onSuccess = { user: UserModel ->
                            navController.navigate("profile") {
                                popUpTo("sign_in") { inclusive = true }
                            }
                        },
                        onFailure = { message ->
                            println("Lỗi đăng nhập: $message")
                        }
                    )
                }
            )
        }

        // Màn hình hồ sơ người dùng
        composable("profile") {
            ProfileScreen(navController = navController)
        }
    }
}

