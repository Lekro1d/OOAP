package com.example.datamapper.vms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.datamapper.R
import com.example.datamapper.databinding.FragmentCreateEmployeeBinding
import com.example.datamapper.classes.Company
import com.example.datamapper.classes.Employee
import com.example.datamapper.rc.EmployeeAdapter

class CreateEmployeeFragment : Fragment(), EmployeeAdapter.ListenerEmployee {
    private var _binding: FragmentCreateEmployeeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CreateEmployeeViewModel
    private val rcAdapter = EmployeeAdapter(this)

    var selectedCompany: Company? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateEmployeeBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(CreateEmployeeViewModel::class.java)

        viewModel.init(requireContext())

        binding.rcEmployee.layoutManager = LinearLayoutManager(requireContext())
        binding.rcEmployee.adapter = rcAdapter

        viewModel.getAllEmployee()
        viewModel.employeeList.observe(viewLifecycleOwner, Observer { employeeList ->
            for(item in employeeList){
                rcAdapter.addEmployee(item)
            }
        })

        viewModel.getAllCompany()
        viewModel.companyList.observe(viewLifecycleOwner, Observer { companyList ->
            fillCompany(companyList)
        })

        binding.btnAddCompany.setOnClickListener{
            if(selectedCompany != null) {
                val employee = Employee(
                    binding.editFName.text.toString(),
                    binding.editLName.text.toString(),
                    selectedCompany!!
                )
                binding.editFName.text.clear()
                binding.editLName.text.clear()

                viewModel.insertData(employee)
                rcAdapter.addEmployee(employee)
            }
            else {
                Toast.makeText(requireContext(), "Выберите компанию", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    fun fillCompany(value: List<Company>) {
        val adapterCompany = ArrayAdapter(requireContext(), R.layout.list_item, value)
        binding.autoTextCompanyName.setAdapter(adapterCompany)

        binding.autoTextCompanyName.setOnItemClickListener { _, _, position, _ ->
            selectedCompany = value[position]
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.close()
    }

    override fun onClickEmployeeItem(employee: Employee) {
        Toast.makeText(requireContext(), "Вы выбрали сотрудника(-цу) ${employee.firstName} ${employee.lastName}", Toast.LENGTH_SHORT).show()

        viewModel.removeEmployee(employee)
        rcAdapter.removeEmployee()
        viewModel.getAllEmployee()
    }
}