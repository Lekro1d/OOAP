package com.example.datamapper.rc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.datamapper.R
import com.example.datamapper.databinding.RcCompanyLayoutBinding
import com.example.datamapper.classes.Company

class CompanyAdapter(val listener: ListenerCompany): RecyclerView.Adapter<CompanyAdapter.CompanyHolder>() {
    private val companyList = ArrayList<Company>()

    class CompanyHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = RcCompanyLayoutBinding.bind(item)
        fun bind(dataCompany: Company, listener: ListenerCompany) = with(binding) {
            tvCompanyName.text = dataCompany.name
            tvCompanyAdress.text = dataCompany.adress
            itemView.setOnClickListener {
                listener.onClickCompanyItem(dataCompany)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_company_layout, parent, false)
        return CompanyHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyHolder, position: Int) {
        holder.bind(companyList[position], listener)
    }

    override fun getItemCount(): Int {
        return companyList.size
    }

    fun addCompany(company: Company) {
        companyList.add(company)
        notifyDataSetChanged()
    }

    fun removeCompany() {
        companyList.clear()
        notifyDataSetChanged()
    }

    interface ListenerCompany{
        fun onClickCompanyItem(company: Company)
    }
}