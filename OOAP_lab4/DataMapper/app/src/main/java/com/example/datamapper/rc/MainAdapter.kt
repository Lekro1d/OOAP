package com.example.datamapper.rc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.datamapper.R
import com.example.datamapper.databinding.RcMainLayoutBinding
import com.example.datamapper.classes.Employee

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainHolder>(){
    private val employeeList = ArrayList<Employee>()

    class MainHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = RcMainLayoutBinding.bind(view)

        fun bind(employee: Employee) = with(binding) {
            tvEmployeeFn.text = employee.firstName
            tvEmployeeLn.text = employee.lastName
            tvEmployeeCompany.text = employee.company.name
            tvCompanyAddress.text = employee.company.adress
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_main_layout, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(employeeList[position])
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    fun addEmployee(employee: Employee) {
        employeeList.add(employee)
        notifyDataSetChanged()
    }

    fun removeEmployee() {
        employeeList.clear()
        notifyDataSetChanged()
    }
}