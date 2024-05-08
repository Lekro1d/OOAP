package com.example.datamapper.vms

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datamapper.classes.Company
import com.example.datamapper.db.DBManagerCompany
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateCompanyViewModel: ViewModel() {
    private lateinit var dbManagerCompany: DBManagerCompany

    private val _companyList = MutableLiveData<List<Company>>()
    val companyList: LiveData<List<Company>> get() = _companyList

    fun init(context: Context) {
        dbManagerCompany = DBManagerCompany(context)

        dbManagerCompany.dbManager.openDB()

        getAllCompany()
    }

    fun getAllCompany() {
        viewModelScope.launch(Dispatchers.IO) {
            val dataCompany = dbManagerCompany.readDataCompany()
            withContext(Dispatchers.Main) {
                _companyList.value = dataCompany
            }
        }
    }

    fun insertCompany(company: Company) {
        dbManagerCompany.insertDataCompany(company)
    }

    fun removeCompany(company: Company) {
        dbManagerCompany.deleteDataCompany(company)
    }

    fun close() {
        dbManagerCompany.dbManager.closeDB()
    }
}