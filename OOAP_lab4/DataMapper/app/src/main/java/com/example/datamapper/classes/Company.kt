package com.example.datamapper.classes

class Company(var name: String, var adress: String, var employeeList: ArrayList<Employee>) {
    override fun toString(): String {
        return name
    }
}