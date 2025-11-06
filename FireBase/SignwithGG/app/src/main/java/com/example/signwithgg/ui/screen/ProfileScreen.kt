package com.example.signwithgg.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.signwithgg.viewmodel.ProfileScreen
import com.example.signwithgg.ui.screen.AllowScreen

@Composable
fun ProfileScreen(navController: NavController) {
    val firebaseUser = FirebaseAuth.getInstance().currentUser
    var isSigningOut by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (firebaseUser != null && !isSigningOut) {

            Text(
                text = "Thông tin tài khoản",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.height(150.dp))

            Image(
                painter = rememberAsyncImagePainter(firebaseUser.photoUrl),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileInfoField(
                label = "Name",
                value = firebaseUser.displayName ?: "Không có tên"
            )

            ProfileInfoField(
                label = "Email",
                value = firebaseUser.email ?: "Không có email"
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    navController.navigate("tasks") {
                        popUpTo("profile"){ inclusive = false }
                    }},
                modifier = Modifier.fillMaxWidth(0.6f),
                border = BorderStroke(2.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC7B55B))
            ) {
                Text("Tasks", color = Color.White)
            }
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    navController.navigate("allow") {
                        popUpTo("profile"){ inclusive = false }
                    }},
                modifier = Modifier.fillMaxWidth(0.6f),
                border = BorderStroke(2.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF84C75B))
            ) {
                Text("Allow Permission", color = Color.White)
            }
            Spacer(modifier = Modifier.height(10.dp))


            Button(
                onClick = {
                    isSigningOut = true
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate("sign_in") {
                        popUpTo("profile") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth(0.6f),
                border = BorderStroke(2.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB86152))
            ) {
                Text("Đăng xuất", color = Color.White)
            }
        } else if (isSigningOut) {
            CircularProgressIndicator()
        } else {
            LaunchedEffect(Unit) {
                navController.navigate("sign_in") {
                    popUpTo("profile") { inclusive = true }
                }
            }
        }
    }
}

@Composable
fun ProfileInfoField(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF555555)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xCECBCBCB), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            Text(
                text = value,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    ProfileScreen(navController = NavHostController(LocalContext.current))
}

