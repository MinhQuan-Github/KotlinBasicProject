package com.example.kotlin_android_project3

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [ContactModel::class], version = 1)
abstract class AppDatabase private constructor(): RoomDatabase()  {

    abstract fun contactDao(): ContactDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (this.instance == null) {
                this.instance = Room.databaseBuilder(
                    context = context,
                    klass = AppDatabase::class.java,
                    "Contact"
                ).build()
            }
            return this.instance!!
        }
    }


    override fun clearAllTables() {

    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun createOpenHelper(config: DatabaseConfiguration): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

}