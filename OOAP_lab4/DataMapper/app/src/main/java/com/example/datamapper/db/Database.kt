package com.example.datamapper.db

import android.provider.BaseColumns

object Database {
    const val DATABASE_VERSION = 8
    const val DATABASE_NAME = "mapperDB.db"

    const val TABLE_NAME_COMPANY = "company_table"
    const val COLUMN_NAME = "name"
    const val COLUMN_ADRESS = "adress"

    const val CREATE_TABLE_COMPANY = "CREATE TABLE IF NOT EXISTS $TABLE_NAME_COMPANY (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "$COLUMN_NAME TEXT, " +
            "$COLUMN_ADRESS TEXT)"

    const val SQL_DELETE_COMPANY = "DROP TABLE IF EXISTS $TABLE_NAME_COMPANY"

    const val TABLE_NAME_EMPLOYEE = "employee_table"
    const val COLUMN_FIRST_NAME = "first_name"
    const val COLUMN_LAST_NAME = "last_name"
    const val COLUMN_ID_COMPANY = "company_id"

    const val CREATE_TABLE_EMPLOYEE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME_EMPLOYEE (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "$COLUMN_FIRST_NAME TEXT, " +
            "$COLUMN_LAST_NAME TEXT, " +
            "$COLUMN_ID_COMPANY INTEGER, " +
            "FOREIGN KEY($COLUMN_ID_COMPANY) REFERENCES $TABLE_NAME_COMPANY(${BaseColumns._ID}) ON DELETE CASCADE)"

    const val SQL_DELETE_EMPLOYEE = "DROP TABLE IF EXISTS $TABLE_NAME_EMPLOYEE"
}