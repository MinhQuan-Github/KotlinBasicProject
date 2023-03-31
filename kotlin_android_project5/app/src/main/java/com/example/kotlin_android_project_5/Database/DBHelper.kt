package com.example.kotlin_android_project_5.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.kotlin_android_project_5.Constant.Constant
import com.example.kotlin_android_project_5.Model.ContactModel
import com.example.kotlin_android_project_5.MainActivity.DBService

class DBHelper(context: Context): SQLiteOpenHelper(context, Constant.DB_NAME, null, Constant.DB_VERSION),
    DBService {

    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "create table ${Constant.TABLE_NAME}(${Constant.ID} integer not null primary key autoincrement unique, ${Constant.NAME} text, ${Constant.MOBILE} text, ${Constant.EMAIL} text, ${Constant.PHOTO} BLOG)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    @SuppressLint("Recycle")
    override fun getAllContacts(context: Context): List<ContactModel> {
        val list = ArrayList<ContactModel>()
        val query = "select * from ${Constant.TABLE_NAME}"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val contact = ContactModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                )
                list.add(contact)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun addNewContact(context: Context, contact: ContactModel) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.NAME, contact.name)
        contentValues.put(Constant.MOBILE, contact.mobile)
        contentValues.put(Constant.EMAIL, contact.email)
        database.insert(Constant.TABLE_NAME, null, contentValues)
        database.close()
    }

    override fun updateContact(context: Context, contact: ContactModel): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.ID, contact.id)
        contentValues.put(Constant.NAME, contact.name)
        contentValues.put(Constant.MOBILE, contact.mobile)
        contentValues.put(Constant.EMAIL, contact.email)
        return database.update(
            Constant.TABLE_NAME,
            contentValues,
            "${Constant.ID} = ?",
            arrayOf(contact.id.toString())
        )
    }

    override fun removeContact(context: Context, contact: ContactModel) {
        val database = this.writableDatabase
        database.delete(
            Constant.TABLE_NAME,
            "${Constant.ID} = ?",
            arrayOf(contact.id.toString())
        )
        database.close()
    }

    @SuppressLint("Recycle")
    override fun getContact(context: Context, name: String): List<ContactModel> {
        val list = ArrayList<ContactModel>()
        val query = "select * from ${Constant.TABLE_NAME} where name like '%${name}%'"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val contact = ContactModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                )
                list.add(contact)
            } while (cursor.moveToNext())
        }
        return list
    }

    @SuppressLint("Recycle", "Range")
    override fun getContact(context: Context, id: Int): ContactModel? {
        val query = "SELECT * FROM ${Constant.TABLE_NAME} WHERE id = ?"
        val database = this.readableDatabase

        val cursor = database.rawQuery(query, arrayOf(id.toString()))

        cursor.use {
            if (it.moveToFirst()) {
                return ContactModel(
                    it.getInt(it.getColumnIndex("id")),
                    it.getString(it.getColumnIndex("name")),
                    it.getString(it.getColumnIndex("mobile")),
                    it.getString(it.getColumnIndex("email"))
                )
            }
        }

        return null
    }
}