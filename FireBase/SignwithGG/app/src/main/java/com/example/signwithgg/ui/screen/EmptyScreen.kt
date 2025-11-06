package com.example.signwithgg.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.example.signwithgg.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmptyTaskScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("List", color = Color(0xFF2C9A77), fontWeight = FontWeight.Bold, fontSize = 24.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(start = 120.dp)
                ) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("tasks") }) {
                        Text(
                            text = "<",
                            fontSize = 40.sp
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(

            modifier = Modifier
                .background(color = Color(0x54CECECE))
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.assignment_24px),
                    contentDescription = "No Tasks Image",
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "No Tasks Yet!",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Stay productive — add something to do",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewEmpty() {
    EmptyTaskScreen(navController = NavHostController(LocalContext.current))
}