package com.sgtsoft.bbox2.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sgtsoft.bbox2.data.model.M3uItem
import com.sgtsoft.bbox2.data.repository.M3uItemRepository

class GroupTitleListViewModel(private val repository: M3uItemRepository) : ViewModel() {

    val groupTitles: LiveData<List<String>> = repository.getDistinctGroupTitles()
}
