package com.example.visitor.visitors

import com.example.visitor.items.FastFood
import com.example.visitor.items.Salad

interface Visitor {
    fun visit(fastFood: FastFood)
    fun visit(salad: Salad)
}