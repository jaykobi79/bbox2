package com.sgtsoft.bbox2.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sgtsoft.bbox2.data.repository.M3uItemRepository
import com.sgtsoft.bbox2.fragments.GroupTitleListViewModel

class GroupTitleListViewModelFactory(private val repository: M3uItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroupTitleListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GroupTitleListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
