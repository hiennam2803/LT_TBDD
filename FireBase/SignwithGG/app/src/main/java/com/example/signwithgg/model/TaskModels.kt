package com.example.signwithgg.model

data class ApiResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: List<Task>
)

data class ApiResponseSingle(
    val isSuccess: Boolean,
    val message: String,
    val data: Task?
)

data class Task(
    val id: Int,
    val title: String?,
    val description: String?,
    val status: String?,
    val priority: String?,
    val category: String?,
    val dueDate: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val subtasks: List<Subtask> = emptyList(),
    val attachments: List<Attachment> = emptyList(),
    val reminders: List<Reminder> = emptyList()
) {
    val subComplete: Boolean
        get() = subtasks.all { it.isCompleted }
    val getId: Int
        get() = id
}

data class Subtask(
    val id: Int,
    val title: String,
    val isCompleted: Boolean,

)

data class Attachment(
    val id: Int,
    val fileName: String,
    val fileUrl: String
)

data class Reminder(
    val id: Int,
    val time: String,
    val type: String
)
