package org.d3if3062.asessment1.backend.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3062.asessment1.backend.database.Dao.Dao_Interface
import org.d3if3062.asessment1.backend.database.model.TodoList

@Database(entities = [TodoList::class], version = 1, exportSchema = false)
abstract class TodoList_DB : RoomDatabase() {

    abstract val dao: Dao_Interface
    companion object{
        @Volatile
        private var INSTANCE: TodoList_DB? = null
        fun getInstance(context: Context): TodoList_DB {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoList_DB::class.java,
                        "todo_list_db"
                    ).build()
                    INSTANCE = instance
                }

                return instance
            }
        }

    }
}