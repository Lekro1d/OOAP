package com.example.visitor.items

import com.example.visitor.visitors.Visitor
import java.io.Serializable

class Order : Serializable {
    var foods: MutableList<Food> = arrayListOf()
}