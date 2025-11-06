package com.example.signwithgg.ui.screen

import com.example.signwithgg.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.signwithgg.model.Task
import com.example.signwithgg.viewmodel.TaskViewModel
import androidx.navigation.NavController

@Composable
fun TaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = viewModel()
) {
    val tasks by viewModel.tasks
    val isLoading by viewModel.isLoading
    val error by viewModel.error

    LaunchedEffect(Unit) { viewModel.loadTasks() }

    when {
        isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        error != null -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Lỗi: $error", color = MaterialTheme.colorScheme.error)
        }

        else -> {
            Column(Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_uth),
                        contentDescription = "Task icon",
                        modifier = Modifier
                            .size(75.dp)
                    )
                    Text(
                        text = "Smart Tasks\nQuản lý công việc hiệu quả",
                        color = Color(0xFF2C9A77),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    items(tasks) { task ->
                        TaskCard(task,navController)
                    }
                }
            }
        }
    }
}

@Composable
fun TaskCard(
    task: Task,
    navController: NavController
) {

    val colors = listOf(
        Color(0xFFFFCDD2),
        Color(0xFFC8E6C9),
        Color(0xFFBBDEFB),
        Color(0xFFFFF9C4),
        Color(0xFFD1C4E9)
    )
    val randomColor = remember { colors.random() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {
                navController.navigate("detail/${task.id}")
            },
        colors = CardDefaults.cardColors(containerColor = randomColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .align(Alignment.Top),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (task.subComplete) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Subtasks completed",
                        tint = Color(0xFF1BC9A3),
                        modifier = Modifier.size(30.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Subtasks completed",
                        tint = Color(0xFFB12030) ,
                        modifier = Modifier.size(30.dp)
                    )

                }
            }
            Column {
                // Tiêu đề
                Text(
                    text = task.title?:"",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = task.description?:"",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Status: ${task.status}",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = task.createdAt?:"",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TaskScreen()
//}
