package com.example.signwithgg.ui.screen

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.example.signwithgg.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.signwithgg.service.PermissionService
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllowScreen(navController: NavController) {

    val context = LocalContext.current
    val service = remember { PermissionService(context) }

    val locationLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { perms ->
        val granted = perms[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                perms[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        service.onLocationResult(granted)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        service.onCameraResult(granted)
    }

    val notificationLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        service.onNotificationResult(granted)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Allow permission",
                        color = Color(0xFF2C9A77),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 60.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("profile") }) {
                        Text("<", fontSize = 40.sp)
                    }
                }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .background(Color(0x54CECECE))
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Button(
                    onClick = {
                        locationLauncher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    border = BorderStroke(2.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(Color(0xFF34C9BD))
                ) {
                    Text("Location", color = Color.White, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = {
                        if (service.needNotificationPermission()) {
                            notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        } else {
                            service.onNotificationResult(true)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    border = BorderStroke(2.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(Color(0xFFD5B24A))
                ) {
                    Text("Notification", color = Color.White, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = {
                        cameraLauncher.launch(Manifest.permission.CAMERA)
                    },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    border = BorderStroke(2.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(Color(0xFFD2559A))
                ) {
                    Text("Camera", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}


@Preview
@Composable
fun AllowScreenPreview() {
    AllowScreen(navController = NavHostController(LocalContext.current))
}

