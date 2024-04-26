package com.example.visitor.items

import com.example.visitor.visitors.Visitor

class Salad(
    var name: String = "",
    var rating: Double = 0.0,
    var ingredients: MutableList<String> = mutableListOf(),
    var ingredientsCalories: MutableList<Int> = mutableListOf(),
    var weightFood: Int
): Food {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}