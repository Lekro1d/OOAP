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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.datamapper.R
import com.example.datamapper.databinding.FragmentMainBinding
import com.example.datamapper.classes.Company
import com.example.datamapper.rc.MainAdapter

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private val rcAdapter = MainAdapter()

    var selectedCompany: Company? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.init(requireContext())

        binding.rcMain.layoutManager = LinearLayoutManager(requireContext())
        binding.rcMain.adapter = rcAdapter

        viewModel.getAllCompany()
        viewModel.companyList.observe(viewLifecycleOwner, Observer { companyList ->
            fillCompany(companyList)
        })

        binding.btnAllEmployee.setOnClickListener {
            rcAdapter.removeEmployee()
            viewModel.getAllEmployee()
            viewModel.employeeList.observe(viewLifecycleOwner, Observer { employeeList ->
                rcAdapter.removeEmployee()
                for(item in employeeList){
                    rcAdapter.addEmployee(item)
                }
            })
        }

        binding.btnAddCompany.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_createCompanyFragment)
        }

        binding.btnAddEmployee.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_createEmployeeFragment)
        }

        return view
    }

    fun fillCompany(value: List<Company>) {
        val adapterCompany = ArrayAdapter(requireContext(), R.layout.list_item, value)
        binding.autoTextCompanyName.setAdapter(adapterCompany)

        binding.autoTextCompanyName.setOnItemClickListener { _, _, position, _ ->
            selectedCompany = value[position]

            if(selectedCompany != null) {
                viewModel.getEmployeesCompany(selectedCompany?.name!!)

                viewModel.employeesCompanyList.observe(
                    viewLifecycleOwner,
                    Observer { employeesCompanyList ->
                        rcAdapter.removeEmployee()
                        for (item in employeesCompanyList) {
                            rcAdapter.addEmployee(item)
                        }
                    })
            }
            else {
                Toast.makeText(requireContext(), "Выберите компанию", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.close()
    }
}