package com.example.kamus

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Kata::class], version = 1, exportSchema = false)
abstract class KamusDatabase : RoomDatabase() {

    abstract fun kataDao(): KataDao

    companion object {
        @Volatile
        private var instance: KamusDatabase? = null

        fun getInstance(context: Context): KamusDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    KamusDatabase::class.java,
                    "kamus.db"
                ).build().also { instance = it }
            }
        }
    }
}
