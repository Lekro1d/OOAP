package com.example.datamapper.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DBManager(context: Context) {
    val dbHelper = DBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB() {
        db = dbHelper.writableDatabase
        db?.execSQL("PRAGMA foreign_keys=ON;")
    }

    fun closeDB() {
        dbHelper.close()
    }
}