package org.d3if3062.asessment1.model

data class TodoList(
    val id: Long,
    val title: String,
    val deadLine: String,
    val description: String,
    val status: Boolean,
    val createAt: String
)