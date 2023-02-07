package com.devnuts.ruflu.data.database

import android.content.Context
import androidx.room.*
import com.devnuts.ruflu.data.database.dao.ChatDao


@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
@TypeConverters(OrmConverter::class)
abstract class RufluDatabase : RoomDatabase() {

    abstract fun chatDao(): ChatDao

    companion object {
        @Volatile
        private var INSTANCE: RufluDatabase? = null

        private fun buildDatabase(context: Context): RufluDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                RufluDatabase::class.java,
                "chat"
            ).build()


        fun getInstance(context: Context): RufluDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE= it }
            }
    }
}