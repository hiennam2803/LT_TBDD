package com.example.signwithgg.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signwithgg.model.Task
import com.example.signwithgg.service.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val _taskDetail = MutableStateFlow<Task?>(null)
    val taskDetail: StateFlow<Task?> = _taskDetail

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isSuccess = MutableStateFlow(true)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    fun loadTaskDetail(id: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true

                val result = RetrofitInstance.api.getTaskDetail(id)

                if (!result.isSuccess || result.data == null) {
                    _isSuccess.value = false
                    return@launch
                }

                _taskDetail.value = result.data
                _isSuccess.value = true

            } catch (e: Exception) {

                _isSuccess.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun deleteTask(id: Int, onDeleted: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.deleteTask(id)
                onDeleted(true)
            } catch (e: Exception) {
                onDeleted(false)
            }
        }
    }
}
