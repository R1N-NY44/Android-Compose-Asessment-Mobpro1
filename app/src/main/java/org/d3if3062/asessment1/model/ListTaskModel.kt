package org.d3if3062.asessment1.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ListTaskModel : ViewModel() {
    // Data source
    private val _data = MutableLiveData<List<TodoList>>()
    val data: LiveData<List<TodoList>> = _data

    init {
        // Inisialisasi data awal
        _data.value = getDataDummy()
    }

    // Method untuk menambahkan data baru
    @RequiresApi(Build.VERSION_CODES.O)
    fun addTodo(
        title: String,
        deadLine: String,
        description: String,
        status: Boolean
    ) {
        val currentData = _data.value.orEmpty()

        // Menentukan ID baru dengan increment dari ID terbesar yang ada
        val maxId = currentData.maxByOrNull { it.id }?.id ?: 0L
        val newId = maxId + 1

        // Menggunakan LocalDateTime.now() untuk mendapatkan tanggal dan waktu saat ini
        val createAt = LocalDateTime.now()

        // Menggunakan DateTimeFormatter untuk memformat tanggal dan waktu sesuai dengan format yang diinginkan
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yy - (HH.mm)")
        val formattedDateTime = createAt.format(formatter)

        val newData = currentData + TodoList(newId, title, deadLine, description, status, formattedDateTime)
        _data.value = newData
    }

    // Method untuk mendapatkan data berdasarkan ID
    fun getTaskById(id: Long): TodoList? {
        return _data.value?.find { it.id == id }
    }

    // Metode untuk menandai tugas sebagai selesai
    fun markTask(taskId: Long) {
        val updatedTasks = _data.value.orEmpty().map { task ->
            if (task.id == taskId) {
                task.copy(status = !task.status)
            } else {
                task
            }
        }
        _data.value = updatedTasks
    }

    // Method untuk menghapus data berdasarkan ID
    fun deleteTodoById(id: Long) {
        val newData = _data.value.orEmpty().filter { it.id != id }
        _data.value = newData
    }

    // Method untuk memperbarui data berdasarkan ID
    fun updateTodoById(
        id: Long,
        judul: String,
        deadLine: String,
        description: String,
        status: Boolean,
        createAt: String
    ) {
        val newData = _data.value.orEmpty().map {
            if (it.id == id) {
                TodoList(id, judul, deadLine, description, status, createAt)
            } else {
                it
            }
        }
        _data.value = newData
    }

    fun getAllTasks(): LiveData<List<TodoList>> {
        return data
    }

    // Membuat data dummy
    private fun getDataDummy(): List<TodoList> {
        val data = mutableListOf<TodoList>()
        for (i in 3 downTo 1) {
            data.add(
                TodoList(
                    id = i.toLong(),
                    title = "Kuliah Mobpro $i",
                    deadLine = "0$i/0$i/2024 2$i:2$i",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                    status = false,
                    createAt = "0$i/0$i/2024 - (2$i.59)"
                )
            )
        }
        return data
    }
}