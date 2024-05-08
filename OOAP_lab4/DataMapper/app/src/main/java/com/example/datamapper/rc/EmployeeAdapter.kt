package com.example.datamapper.rc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.datamapper.R
import com.example.datamapper.databinding.RcEmployeeLayoutBinding
import com.example.datamapper.classes.Employee

class EmployeeAdapter(val listener: EmployeeAdapter.ListenerEmployee): RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder>() {
    private val employeeList = ArrayList<Employee>()

    class EmployeeHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = RcEmployeeLayoutBinding.bind(item)
        fun bind(dataEmployee: Employee, listener: ListenerEmployee) = with(binding) {
            tvEmployeeFn.text = dataEmployee.firstName
            tvEmployeeLn.text = dataEmployee.lastName
            tvEmployeeCompany.text = dataEmployee.company.name
            itemView.setOnClickListener{
                listener.onClickEmployeeItem(dataEmployee)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_employee_layout, parent, false)
        return EmployeeHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {
        holder.bind(employeeList[position], listener)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    fun addEmployee(employee: Employee){
        employeeList.add(employee)
        notifyDataSetChanged()
    }

    fun removeEmployee() {
        employeeList.clear()
        notifyDataSetChanged()
    }

    interface ListenerEmployee {
        fun onClickEmployeeItem(employee: Employee)
    }
}