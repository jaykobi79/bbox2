package com.sgtsoft.bbox2.data.model

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "m3u_items")
data class M3uItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "xui_id") val xuiId: String?,
    @ColumnInfo(name = "tvg_id") val tvgId: String?,
    @ColumnInfo(name = "tvg_name") val tvgName: String?,
    @ColumnInfo(name = "tvg_logo") val tvgLogo: String?,
    @ColumnInfo(name = "group_title") val groupTitle: String?,
    @ColumnInfo(name = "channel_name") val channelName: String?,
    @ColumnInfo(name = "stream_url") val streamUrl: String?
)


