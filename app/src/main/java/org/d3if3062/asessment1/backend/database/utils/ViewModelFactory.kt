package org.d3if3062.asessment1.backend.database.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3062.asessment1.backend.database.Dao.Dao_Interface
import org.d3if3062.asessment1.backend.database.MainViewModel

class ViewModelFactory(private val dao: Dao_Interface) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModel::class.java)){
            return MainViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}