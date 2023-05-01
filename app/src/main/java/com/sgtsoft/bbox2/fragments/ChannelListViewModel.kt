package com.sgtsoft.bbox2.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sgtsoft.bbox2.data.dao.M3uItemDao
import com.sgtsoft.bbox2.data.model.M3uItem
import com.sgtsoft.bbox2.data.repository.M3uItemRepository
import com.sgtsoft.bbox2.parser.M3uChannel
import kotlinx.coroutines.Dispatchers

class ChannelListViewModel(private val m3uItemDao: M3uItemDao) : ViewModel() {



    fun getTvgIdsByGroupTitle(groupTitle: String): LiveData<List<M3uItem>> = liveData(Dispatchers.IO) {
        emit(m3uItemDao.getChannelsByGroupTitle(groupTitle))
    }

}










