package com.example.manlib.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.manlib.viewmodel.LibraryViewModel
import com.example.manlib.model.Book
import com.example.manlib.model.Student

@Composable
fun LibraryScreen(viewModel: LibraryViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // --- Tiêu đề ---
        Text(
            text = "Hệ thống\nQuản lý Thư viện",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        // --- Sinh viên ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = viewModel.currentStudent.name,
                onValueChange = {},
                readOnly = true,
                label = { Text("Sinh viên") },
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.width(8.dp))

            Button(onClick = { viewModel.toggleStudentList() }) {
                Text("Thay đổi")
            }
        }

        // --- Danh sách sinh viên ---
        if (viewModel.showStudentList) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                items(viewModel.students) { student ->
                    StudentItem(
                        student = student,
                        selected = (student == viewModel.currentStudent),
                        bookCount = viewModel.getSelectedBookCount(student.id),
                        onClick = { viewModel.changeStudent(student) }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // --- Khung danh sách sách đã chọn ---
        Text("Danh sách sách", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp)
                .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            val selectedBooks = viewModel.books.filter { it.isSelected }
            if (selectedBooks.isEmpty()) {
                Text("Chưa có sách nào được chọn.", color = Color.Gray)
            } else {
                LazyColumn {
                    items(selectedBooks) { book ->
                        SelectedBookItem(book)
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // --- Nút thêm để hiện danh sách chọn ---
        Button(
            onClick = { viewModel.toggleBookList() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(if (viewModel.showBookList) "Ẩn danh sách" else "Thêm")
        }

        // --- Danh sách tất cả sách (chọn thêm) ---
        if (viewModel.showBookList) {
            Spacer(Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                items(viewModel.books) { book ->
                    BookItem(book = book, onToggle = { viewModel.toggleBookSelection(book.id) })
                }
            }
        }
    }
}

@Composable
fun StudentItem(student: Student, selected: Boolean, bookCount: Int, onClick: () -> Unit) {
    val bgColor = if (selected) Color(0xFFD6EAF8) else Color.White
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Column {
            Text(student.name, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun BookItem(book: Book, onToggle: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .clickable { onToggle() }
            .padding(8.dp)
    ) {
        Checkbox(checked = book.isSelected, onCheckedChange = { onToggle() })
        Spacer(Modifier.width(8.dp))
        Text(book.title)
    }
}

@Composable
fun SelectedBookItem(book: Book) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Checkbox(checked = true, onCheckedChange = null)
        Spacer(Modifier.width(8.dp))
        Text(book.title)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LibraryScreenPreview() {
    LibraryScreen()
}

