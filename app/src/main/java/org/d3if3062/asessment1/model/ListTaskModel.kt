package org.d3if3062.asessment1.model

import androidx.lifecycle.ViewModel

class ListTaskModel : ViewModel() {

    val data = getDataDummy()

    private fun getDataDummy(): List<TodoList> {
        val data = mutableListOf<TodoList>()
        for (i in 29 downTo 20) {
            data.add(
                TodoList(
                    id = i.toLong(),
                    judul = "Kuliah Mobpro $i Maret",
                    deadLine = "2024-03-$i- 00:00:00",
                    description = "Yey, hari ini belajar membuat aplikasi Android list dan berhasil. Hehe.. Mudah2an modul selanjuta juga lancar $i. Astungkara.",
                    status = false,
                    createAt = "2024-03-$i- 00:00:00"
                )
            )

        }
        return data
    }

}