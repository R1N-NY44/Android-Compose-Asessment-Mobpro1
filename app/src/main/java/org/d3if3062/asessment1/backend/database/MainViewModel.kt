package org.d3if3062.asessment1.backend.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.d3if3062.asessment1.backend.database.Dao.Dao_Interface
import org.d3if3062.asessment1.backend.database.model.TodoList

class MainViewModel(val dao: Dao_Interface) : ViewModel() {

    // Membuat flow data yang akan di observe oleh UI
    val data: StateFlow<List<TodoList>> = dao.getTodoList().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    // Fungsi untuk mengambil data todo list berdasarkan ID
    suspend fun getTodoListById(id: Long): TodoList? {
        return dao.getTodoListById(id)
    }

    // Fungsi untuk menambahkan data todo list
    fun insert(title: String, deadLine: String, description: String, status: Boolean, createAt: String) {
        val todoList = TodoList(
            title = title,
            deadLine = deadLine,
            description = description,
            status = status,
            createAt = createAt
        )
        viewModelScope.launch {
            dao.insert(todoList)
        }
    }

    // Fungsi untuk mengupdate data todo list
    fun update(id: Long, title: String, deadLine: String, description: String, status: Boolean, createAt: String){
        val todoList = TodoList(
            id = id,
            title = title,
            deadLine = deadLine,
            description = description,
            status = status,
            createAt = createAt
        )
        viewModelScope.launch {
            dao.update(todoList)
        }
    }

    // Fungsi untuk menghapus data todo list berdasarkan ID
    fun deleteTodoListById(id: Long){
        viewModelScope.launch {
            dao.deleteTodoListById(id)
        }
    }

    // Fungsi untuk menandai todo list sebagai selesai
    fun markAsDone(id: Long) {
        viewModelScope.launch {
            val todoList = dao.getTodoListById(id)
            todoList?.let {
                dao.update(it.copy(status = true))
            }
        }
    }

}