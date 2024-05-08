package com.example.datamapper.vms

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.datamapper.databinding.FragmentCreateCompanyBinding
import com.example.datamapper.db.DBManagerCompany
import com.example.datamapper.classes.Company
import com.example.datamapper.rc.CompanyAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateCompanyFragment : Fragment(), CompanyAdapter.ListenerCompany {
    private var _binding: FragmentCreateCompanyBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CreateCompanyViewModel
    private val rcAdapter = CompanyAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateCompanyBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(CreateCompanyViewModel::class.java)
        binding.rcCompany.layoutManager = LinearLayoutManager(requireContext())
        binding.rcCompany.adapter = rcAdapter

        viewModel.init(requireContext())

        viewModel.companyList.observe(viewLifecycleOwner, Observer { companyList ->
            for(item in companyList){
                rcAdapter.addCompany(item)
            }
        })

        binding.btnAddCompany.setOnClickListener{
            val company = Company(binding.editName.text.toString(), binding.editAdress.text.toString(), ArrayList())
            binding.editName.text.clear()
            binding.editAdress.text.clear()

            viewModel.insertCompany(company)
            rcAdapter.addCompany(company)
        }

        return view
    }

    override fun onClickCompanyItem(company: Company) {
        Toast.makeText(requireContext(), "Вы выбрали ${company.name}", Toast.LENGTH_SHORT).show()

        viewModel.removeCompany(company)
        rcAdapter.removeCompany()
        viewModel.getAllCompany()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.close()
    }
}