package org.d3if3062.asessment1.backend.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list")
data class TodoList(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val deadLine: String,
    val description: String,
    val status: Boolean,
    val createAt: String
)