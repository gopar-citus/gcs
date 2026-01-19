package com.example.gcs.ui.demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.gcs.data.model.DemoItem
import com.example.gcs.data.repository.DemoRepository
import kotlinx.coroutines.launch

class DemoViewModel(private val repository: DemoRepository) : ViewModel() {

    val items: LiveData<List<DemoItem>> = repository.items.asLiveData()

    init {
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            repository.refreshItems()
        }
    }
}

class DemoViewModelFactory(private val repository: DemoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DemoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DemoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
