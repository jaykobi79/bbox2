package com.sgtsoft.bbox2.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgtsoft.bbox2.data.model.M3uItem
import com.sgtsoft.bbox2.data.repository.M3uItemRepository
import kotlinx.coroutines.launch

class GroupTitleListViewModel(private val repository: M3uItemRepository) : ViewModel() {

    private val _groupTitles = MutableLiveData<List<String>>()
    val groupTitles: LiveData<List<String>>
        get() = _groupTitles

    init {
        refreshGroupTitles()
    }

    private fun refreshGroupTitles() {
        viewModelScope.launch {
            val titles = repository.getGroupTitles()
            _groupTitles.value = titles
        }
    }
}




