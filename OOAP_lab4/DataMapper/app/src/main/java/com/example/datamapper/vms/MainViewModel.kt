package com.example.datamapper.vms

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datamapper.db.DBManagerCompany
import com.example.datamapper.db.DBManagerEmployee
import com.example.datamapper.classes.Company
import com.example.datamapper.classes.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    private lateinit var dbManagerEmployee: DBManagerEmployee
    private lateinit var dbManagerCompany: DBManagerCompany

    private val _companyList = MutableLiveData<List<Company>>()
    val companyList: LiveData<List<Company>> get() = _companyList

    private val _employeeList = MutableLiveData<List<Employee>>()
    val employeeList: LiveData<List<Employee>> get() = _employeeList

    private val _employeesCompanyList = MutableLiveData<List<Employee>>()
    val employeesCompanyList: LiveData<List<Employee>> get() = _employeesCompanyList

    fun init(context: Context){
        dbManagerCompany = DBManagerCompany(context)
        dbManagerEmployee = DBManagerEmployee(context)

        dbManagerEmployee.dbManager.openDB()
        dbManagerCompany.dbManager.openDB()
    }

    fun getAllCompany() {
        viewModelScope.launch(Dispatchers.IO) {
            val dataCompany = dbManagerCompany.readDataCompany()
            withContext(Dispatchers.Main) {
                _companyList.value = dataCompany
            }
        }
    }

    fun getAllEmployee() {
        viewModelScope.launch(Dispatchers.IO) {
            val dataList = dbManagerEmployee.readDataEmployee(dbManagerCompany)
            withContext(Dispatchers.Main) {
                _employeeList.value = dataList
                dataList.clear()
            }
        }
    }

    fun getEmployeesCompany(nameCompany: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val dataList = dbManagerEmployee.getEmployeesByCompany(nameCompany)
            withContext(Dispatchers.Main) {
                _employeesCompanyList.value = dataList
                dataList.clear()
            }
        }
    }

    fun close(){
        dbManagerEmployee.dbManager.closeDB()
        dbManagerCompany.dbManager.closeDB()
    }
}