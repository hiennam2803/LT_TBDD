package com.example.manlib.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.manlib.model.Book
import com.example.manlib.model.Student

class LibraryViewModel : ViewModel() {

    val students = listOf(
        Student(1, "Nguyen Van A"),
        Student(2, "Tran Thi B"),
        Student(3, "Le Van C")
    )

    var currentStudent by mutableStateOf(students.first())
        private set

    var showStudentList by mutableStateOf(false)
    var showBookList by mutableStateOf(false)

    // Mỗi sinh viên có danh sách sách riêng
    private val studentBooks = mutableStateMapOf<Int, List<Book>>()

    var books by mutableStateOf(emptyList<Book>())
        private set

    init {
        // Mỗi sinh viên đều có danh sách sách riêng
        students.forEach { student ->
            studentBooks[student.id] = listOf(
                Book(1, "Sách 01"),
                Book(2, "Sách 02"),
                Book(3, "Sách 03")
            )
        }
        books = studentBooks[currentStudent.id] ?: emptyList()
    }

    fun changeStudent(student: Student) {
        currentStudent = student
        books = studentBooks[student.id] ?: emptyList()
        showStudentList = false
        showBookList = false
    }

    fun toggleStudentList() {
        showStudentList = !showStudentList
    }

    fun toggleBookList() {
        showBookList = !showBookList
    }

    fun toggleBookSelection(bookId: Int) {
        val updatedList = books.map {
            if (it.id == bookId) it.copy(isSelected = !it.isSelected) else it
        }
        books = updatedList
        studentBooks[currentStudent.id] = updatedList
    }

    fun getSelectedBookCount(studentId: Int): Int {
        return studentBooks[studentId]?.count { it.isSelected } ?: 0
    }
}
