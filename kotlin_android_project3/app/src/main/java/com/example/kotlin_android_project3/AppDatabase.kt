package com.example.kotlin_android_project3

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

object AppDatabase {
    private var database: MyRoomDatabase? = null

    fun getInstance(context: Context): MyRoomDatabase {
        if (database == null) {
            synchronized(MyRoomDatabase::class.java) {
                if (database == null) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        MyRoomDatabase::class.java,
                        "Contact"
                    ).build()
                }
            }
        }
        return database!!
    }
}

@Database(entities = [ContactModel::class], version = 1)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}