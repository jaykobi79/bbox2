package com.sgtsoft.bbox2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sgtsoft.bbox2.data.dao.M3uItemDao
import com.sgtsoft.bbox2.data.model.M3uItem

@Database(entities = [M3uItem::class], version = 1, exportSchema = false)
abstract class M3uItemDatabase : RoomDatabase() {

    abstract fun m3uItemDao(): M3uItemDao

    companion object {
        @Volatile
        private var INSTANCE: M3uItemDatabase? = null

        fun getDatabase(context: Context): M3uItemDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    M3uItemDatabase::class.java,
                    "m3u_item_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}