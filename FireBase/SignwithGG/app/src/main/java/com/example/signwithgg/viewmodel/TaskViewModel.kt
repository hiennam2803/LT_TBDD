package com.example.signwithgg.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.signwithgg.model.Task
import com.example.signwithgg.service.RetrofitInstance
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val _tasks = mutableStateOf<List<Task>>(emptyList())
    val tasks: State<List<Task>> = _tasks

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun loadTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val res = RetrofitInstance.api.getTasks()
                if (res.isSuccess) _tasks.value = res.data else _error.value = res.message
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
