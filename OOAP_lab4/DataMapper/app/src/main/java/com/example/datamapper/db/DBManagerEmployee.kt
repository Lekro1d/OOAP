package com.example.datamapper.db

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.datamapper.db.Database.COLUMN_ADRESS
import com.example.datamapper.db.Database.COLUMN_FIRST_NAME
import com.example.datamapper.db.Database.COLUMN_ID_COMPANY
import com.example.datamapper.db.Database.COLUMN_LAST_NAME
import com.example.datamapper.db.Database.COLUMN_NAME
import com.example.datamapper.db.Database.TABLE_NAME_COMPANY
import com.example.datamapper.db.Database.TABLE_NAME_EMPLOYEE
import com.example.datamapper.classes.Company
import com.example.datamapper.classes.Employee

class DBManagerEmployee(context: Context) {
    val dbManager = DBManager(context)

    fun insertDataEmployee(employee: Employee, dbCompany: DBManagerCompany) {
        val companyId = dbCompany.getCompanyId(employee.company.name)
        if(companyId != -1L) {
            val values = ContentValues().apply {
                put(COLUMN_FIRST_NAME, employee.firstName)
                put(COLUMN_LAST_NAME, employee.lastName)
                put(COLUMN_ID_COMPANY, companyId)
            }
            dbManager.db?.insert(Database.TABLE_NAME_EMPLOYEE, null, values)
        }
        else {

        }
    }

    fun readDataEmployee(dbCompany: DBManagerCompany): ArrayList<Employee> {
        val dataEmployee = ArrayList<Employee>()
        val cursor = dbManager.db?.query(Database.TABLE_NAME_EMPLOYEE,
            null, null, null, null, null, null)

        while (cursor?.moveToNext()!!) {
            val dataFName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME))
            val dataLName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME))
            val dataIdCompany = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_COMPANY))

            val objCompany = dbCompany.getCompanyById(dataIdCompany)
            val employee = Employee(dataFName, dataLName, objCompany)

            dataEmployee.add(employee)
        }
        cursor.close()
        return dataEmployee
    }

    fun getEmployeesByCompany(companyName: String): ArrayList<Employee> {
        val dataEmployees = ArrayList<Employee>()
        val query = "SELECT $TABLE_NAME_EMPLOYEE.$COLUMN_FIRST_NAME, $TABLE_NAME_EMPLOYEE.$COLUMN_LAST_NAME, $TABLE_NAME_COMPANY.$COLUMN_NAME, $TABLE_NAME_COMPANY.$COLUMN_ADRESS " +
                "FROM $TABLE_NAME_EMPLOYEE " +
                "INNER JOIN $TABLE_NAME_COMPANY ON $TABLE_NAME_EMPLOYEE.$COLUMN_ID_COMPANY = $TABLE_NAME_COMPANY.${BaseColumns._ID} " +
                "WHERE $TABLE_NAME_COMPANY.$COLUMN_NAME = ?"

        val cursor = dbManager.db?.rawQuery(query, arrayOf(companyName))

        if (cursor != null) {
            while (cursor.moveToNext()) {
                val firstName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME))
                val lastName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME))
                val company = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADRESS))

                val employee = Employee(firstName, lastName, Company(company, address, ArrayList()))
                dataEmployees.add(employee)
            }
        }

        cursor?.close()
        return dataEmployees
    }

    fun deleteDataEmployee(employee: Employee) {
        val selection = "${Database.COLUMN_FIRST_NAME} = ? AND ${Database.COLUMN_LAST_NAME} = ?"
        val selectionArgs = arrayOf(employee.firstName, employee.lastName)
        dbManager.db?.delete(Database.TABLE_NAME_EMPLOYEE, selection, selectionArgs)
    }
}