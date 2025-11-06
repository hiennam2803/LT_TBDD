package com.example.signwithgg.ui.screen

import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.signwithgg.viewmodel.DetailViewModel
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.signwithgg.model.*
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.platform.LocalUriHandler



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    taskId: Int,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val taskDetail by viewModel.taskDetail.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    val context = LocalContext.current

    val isSuccess by viewModel.isSuccess.collectAsState()

    LaunchedEffect(taskId) {
        viewModel.loadTaskDetail(taskId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Detail", color = Color(0xFF2C9A77), fontWeight = FontWeight.Bold, fontSize = 24.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(start = 120.dp)
                    ) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text("<", fontSize = 40.sp)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.deleteTask(taskId) { success ->
                            if (success) {
                                Toast.makeText(context, "Xoá thành công!", Toast.LENGTH_SHORT).show()
                                navController.navigate("empty") {
                                    popUpTo("home") { inclusive = true }
                                }
                            } else {
                                Toast.makeText(context, "Xoá thất bại, vui lòng thử lại!", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color(0xFFFF6B6B)
                        )
                    }
                }
            )
        }
    ) { padding ->

        when {
            isLoading -> Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }

            error != null -> Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) { Text("Error: $error", color = Color.Red) }

            taskDetail != null -> {
                val task = taskDetail!!

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp)
                ) {
                    item {
                        // Title + Description
                        Text(
                            text = task.title ?: "",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = task.description ?: "",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )

                        Spacer(Modifier.height(16.dp))


                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            InfoBox("Category", task.category ?: "N/A", Color(0xFFEEEAFB))
                            InfoBox("Status", task.status ?: "N/A", Color(0xFFE8E7FD))
                            InfoBox("Priority", task.priority ?: "N/A", Color(0xFFFFE7E7))
                        }

                        Spacer(Modifier.height(24.dp))

                        // Subtasks
                        Text(
                            "Subtasks",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                        Spacer(Modifier.height(8.dp))

                        task.subtasks.forEach { sub ->
                            SubtaskItem(sub.title, sub.isCompleted)
                            Spacer(Modifier.height(8.dp))
                        }

                        Spacer(Modifier.height(16.dp))

                        // Attachments
                        if (task.attachments.isNotEmpty()) {
                            Text(
                                "Attachments",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            )
                            Spacer(Modifier.height(8.dp))
                            task.attachments.forEach { att ->
                                AttachmentItem(att.fileName, att.fileUrl)
                                Spacer(Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }

            else -> Unit
//            Box(
//                Modifier
//                    .fillMaxSize()
//                    .padding(padding),
//                contentAlignment = Alignment.Center
//            ) {
//                Text("No Data Available", color = Color.Gray)
//            }

        }
        LaunchedEffect(error, isSuccess) {
            if (error != null || isSuccess == false) {
                navController.navigate("empty") {
                    popUpTo("empty") { inclusive = true }
                }
            }
        }

    }
}

@Composable
fun InfoBox(title: String, value: String, bgColor: Color) {
    val icon = when (title) {
        "Category" -> Icons.Default.Category
        "Status" -> Icons.AutoMirrored.Filled.ListAlt
        "Priority" -> Icons.Default.EmojiEvents
        else -> Icons.Default.FileOpen
    }
    Column(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .size(120.dp, 80.dp)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF1BC9A3),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Text(title, fontWeight = FontWeight.Medium, fontSize = 18.sp)
                Text(value, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
        }

    }
}

@Composable
fun SubtaskItem(title: String, completed: Boolean) {
    Row(
        modifier = Modifier

            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xDADBDBDB))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = completed, onCheckedChange = null)
        Spacer(Modifier.width(8.dp))
        Text(title, fontSize = 14.sp)
    }
}

@Composable
fun AttachmentItem(fileName: String, fileUrl: String) {
    val uriHandler = LocalUriHandler.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xBAD0D0D0))
            .clickable {

                uriHandler.openUri(fileUrl)
            }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.FileOpen, contentDescription = null, tint = Color(0xFF1BC9A3))
        Spacer(Modifier.width(8.dp))
        Text(fileName, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewDetail() {
    DetailScreen(navController = NavHostController(LocalContext.current), taskId = 1)
}

