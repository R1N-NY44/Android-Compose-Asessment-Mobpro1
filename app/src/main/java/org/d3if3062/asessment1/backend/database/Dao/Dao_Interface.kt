package org.d3if3062.asessment1.backend.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3062.asessment1.backend.database.model.TodoList

@Dao
interface Dao_Interface {

    @Insert
    suspend fun insert(todoList: TodoList)

    @Update
    suspend fun update(todoList: TodoList)

    @Query("SELECT * FROM todo_list ORDER BY title DESC")
    fun getTodoList(): Flow<List<TodoList>>

    @Query("SELECT * FROM todo_list WHERE id = :id")
    suspend fun getTodoListById(id: Long): TodoList?

    @Query("DELETE FROM todo_list WHERE id = :id")
    suspend fun deleteTodoListById(id: Long)

}