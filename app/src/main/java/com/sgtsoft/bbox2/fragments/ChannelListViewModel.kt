package com.sgtsoft.bbox2.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sgtsoft.bbox2.data.model.M3uItem
import com.sgtsoft.bbox2.data.repository.M3uItemRepository

class ChannelListViewModel(private val m3uItemRepository: M3uItemRepository) : ViewModel() {
    private val _channels = MutableLiveData<List<M3uItem>>()
    val channels: LiveData<List<M3uItem>> = _channels

    suspend fun getChannelsByGroupTitle(groupTitle: String) {
        _channels.value = m3uItemRepository.getChannelsByGroupTitle(groupTitle)
    }
}



