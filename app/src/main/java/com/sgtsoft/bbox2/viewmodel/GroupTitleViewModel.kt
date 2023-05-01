package com.sgtsoft.bbox2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgtsoft.bbox2.data.repository.M3uItemRepository
import kotlinx.coroutines.launch

class GroupTitleViewModel(private val m3uItemRepository: M3uItemRepository) : ViewModel() {

    val groupTitles = MutableLiveData<List<String>>()

    init {
        viewModelScope.launch {
            groupTitles.postValue(m3uItemRepository.getGroupTitles())
        }
    }
}
