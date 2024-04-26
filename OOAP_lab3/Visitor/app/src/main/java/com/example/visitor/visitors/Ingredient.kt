package com.example.visitor.visitors

import com.example.visitor.items.FastFood
import com.example.visitor.items.Salad

class Ingredient: Visitor {
    var ingredientsText = ""

    override fun visit(fastFood: FastFood) {
        ingredientsText += "${fastFood.name}\n"

        for (ingredient in fastFood.ingredients) {
            ingredientsText += "$ingredient\n"
        }
        ingredientsText += "Количество: ${fastFood.countConcreteProduct} шт\n\n"
    }

    override fun visit(salad: Salad) {
        ingredientsText += "${salad.name}\n"

        for (ingredient in salad.ingredients) {
            ingredientsText += "$ingredient\n"
        }
        ingredientsText += "Вес салата: ${salad.weightFood} гр\n\n"
    }
}