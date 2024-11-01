package com.example.listshopingprogect.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShopItemDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shopListDao(): ShopListDao

    companion object {

        private var instance: AppDatabase? = null

        fun getDb(application: Application): AppDatabase {
            return instance ?: Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                "shop_item.db"
            ).build().also { instance = it }
        }
    }
}