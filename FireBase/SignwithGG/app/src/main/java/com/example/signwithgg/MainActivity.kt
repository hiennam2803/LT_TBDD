package com.example.signwithgg

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.signwithgg.model.UserModel
import com.example.signwithgg.ui.screen.*
import com.example.signwithgg.viewmodel.SignInViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.d("FCM_TOKEN", token)
            }
        }

        setContent {
            MaterialTheme {
                Surface {
                    SignWithGoogleApp()
                }
            }
        }
    }
}

@Composable
fun SignWithGoogleApp(
    signInViewModel: SignInViewModel = viewModel(),
) {
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
                    signInViewModel.signInWithGoogle(
                        context = context,
                        launcher = launcher,
                        onSuccess = { _: UserModel ->
                            navController.navigate("profile") {
                                popUpTo("sign_in") { inclusive = true }
                            }
                        },
                        onFailure = { message ->
                            Log.e("LOGIN_ERROR", message)
                        }
                    )
                }
            )
        }

        composable("allow") {
            AllowScreen(navController = navController)
        }


        // Màn hình hồ sơ
        composable("profile") {
            ProfileScreen(
                navController = navController,
            )
        }

        // Màn hình danh sách Task
        composable("tasks") {
            TaskScreen(navController = navController)
        }

        // Chi tiết Task
        composable("detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: 0
            DetailScreen(navController = navController, taskId = taskId)
        }

        // Màn hình trống
        composable("empty") {
            EmptyTaskScreen(navController = navController)
        }
    }
}
