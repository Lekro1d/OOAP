package com.example.datamapper.db

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.datamapper.classes.Company

class DBManagerCompany(context: Context) {
    val dbManager = DBManager(context)

    fun insertDataCompany(company: Company) {
        val values = ContentValues().apply {
            put(Database.COLUMN_NAME, company.name)
            put(Database.COLUMN_ADRESS, company.adress)
        }
        dbManager.db?.insert(Database.TABLE_NAME_COMPANY, null, values)
    }

    fun readDataCompany(): ArrayList<Company> {
        val dataCompany = ArrayList<Company>()
        val cursor = dbManager.db?.query(Database.TABLE_NAME_COMPANY,
            null, null, null, null, null, null)

        while (cursor?.moveToNext()!!) {
            val dataName = cursor.getString(cursor.getColumnIndexOrThrow(Database.COLUMN_NAME))
            val dataAdress = cursor.getString(cursor.getColumnIndexOrThrow(Database.COLUMN_ADRESS))
            val company = Company(dataName, dataAdress, ArrayList())
            dataCompany.add(company)
        }

        cursor.close()
        return dataCompany
    }

    fun getCompanyId(companyName: String): Long {
        val db = dbManager.db
        val columns = arrayOf(BaseColumns._ID)
        val selection = "${Database.COLUMN_NAME} = ?"
        val selectionArgs = arrayOf(companyName)
        val cursor = db?.query(Database.TABLE_NAME_COMPANY, columns, selection, selectionArgs, null, null, null)

        var companyId = -1L
        cursor?.use {
            if (it.moveToFirst()) {
                companyId = it.getLong(it.getColumnIndexOrThrow(BaseColumns._ID))
            }
        }

        return companyId
    }

    fun getCompanyById(companyId: Int): Company {
        var company = Company("", "", ArrayList())

        val cursor = dbManager.db?.query(Database.TABLE_NAME_COMPANY,
            null,
            "${BaseColumns._ID} = ?",
            arrayOf(companyId.toString()),
            null,
            null,
            null)

        cursor?.use {
            if (it.moveToFirst()) {
                var companyName = it.getString(it.getColumnIndexOrThrow(Database.COLUMN_NAME))
                var companyAddress = it.getString(it.getColumnIndexOrThrow(Database.COLUMN_ADRESS))
                company.name = companyName
                company.adress = companyAddress
                company.employeeList = ArrayList()
            }
        }

        return company
    }

    fun deleteDataCompany(company: Company) {
        val selection = "${Database.COLUMN_NAME} = ? AND ${Database.COLUMN_ADRESS} = ?"
        val selectionArgs = arrayOf(company.name, company.adress)
        dbManager.db?.delete(Database.TABLE_NAME_COMPANY, selection, selectionArgs)
    }
}