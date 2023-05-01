package com.sgtsoft.bbox2.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sgtsoft.bbox2.data.model.M3uItem

@Dao
interface M3uItemDao {

    @Query("SELECT * FROM m3u_items")
    fun getAll(): LiveData<List<M3uItem>>

    @Query("SELECT * FROM m3u_items")
    suspend fun getAllItems(): List<M3uItem>

    @Insert
    suspend fun insert(m3uItem: M3uItem)

    @Query("DELETE FROM m3u_items")
    suspend fun deleteAll(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(m3uItems: List<M3uItem>)

    // Add the getChannelsByGroupTitle function here
    @Query("SELECT * FROM m3u_items WHERE group_title = :groupTitle")
    suspend fun getChannelsByGroupTitle(groupTitle: String): List<M3uItem>

    @Query("SELECT DISTINCT group_title FROM m3u_items ORDER BY group_title ASC")
    fun getDistinctGroupTitles(): LiveData<List<String>>

    @Query("SELECT DISTINCT group_title FROM m3u_items")
    suspend fun getGroupTitles(): List<String>

    @Query("SELECT tvg_id FROM m3u_items WHERE group_title = :groupTitle")
    suspend fun getTvgIdsByGroupTitle(groupTitle: String): List<String>


}


