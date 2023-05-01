package com.sgtsoft.bbox2.data.repository

import androidx.lifecycle.LiveData
import com.sgtsoft.bbox2.data.dao.M3uItemDao
import com.sgtsoft.bbox2.data.model.M3uItem

class M3uItemRepository(private val m3uItemDao: M3uItemDao) {

    val allM3uItems: LiveData<List<M3uItem>> = m3uItemDao.getAll()

    suspend fun insert(m3uItem: M3uItem) {
        m3uItemDao.insert(m3uItem)
    }

    suspend fun deleteAll() {
        m3uItemDao.deleteAll()
    }

    suspend fun insertAll(m3uItems: List<M3uItem>) {
        m3uItemDao.insertAll(m3uItems)
    }

    suspend fun getAll(): List<M3uItem> {
        return m3uItemDao.getAllItems()
    }

    // Add the getChannelsByGroupTitle function here
    suspend fun getChannelsByGroupTitle(groupTitle: String): List<M3uItem> {
        return m3uItemDao.getChannelsByGroupTitle(groupTitle)
    }

    suspend fun getGroupTitles(): List<String> {
        return m3uItemDao.getGroupTitles()
    }

    suspend fun getTvgIdsByGroupTitle(groupTitle: String): List<String> {
        return m3uItemDao.getTvgIdsByGroupTitle(groupTitle)
    }


}
