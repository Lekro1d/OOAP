package com.example.datamapper.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, Database.DATABASE_NAME, null, Database.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Database.CREATE_TABLE_COMPANY)
        db?.execSQL(Database.CREATE_TABLE_EMPLOYEE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(Database.SQL_DELETE_COMPANY)
        db?.execSQL(Database.SQL_DELETE_EMPLOYEE)
        onCreate(db)
    }
}