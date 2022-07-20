package com.example.learningcompose.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel : ViewModel() {

    private val _tasks = getWellnessTasks().toMutableStateList()

    val tasks: List<WellnessTask> get() = _tasks

    fun removeTask(task: WellnessTask) {
        _tasks.remove(task)
    }

    fun changedTaskChecked(task: WellnessTask, checked: Boolean) {
        tasks.find { it.id == task.id }?.let { it ->
            it.checked = checked
        }
    }
}

class WellnessTask(
    val id: Int,
    val label: String,
    initialChecked: Boolean = false,
) {
    var checked by mutableStateOf(initialChecked)
}

fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }
