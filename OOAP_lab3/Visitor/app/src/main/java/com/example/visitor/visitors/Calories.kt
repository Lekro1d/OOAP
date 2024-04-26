package com.example.visitor.visitors

import com.example.visitor.items.FastFood
import com.example.visitor.items.Salad

class Calories: Visitor {
    var calsSum = 0
    var ingredientText = ""

    override fun visit(fastFood: FastFood) {
        ingredientText += "${fastFood.name}\n"
        for (i in 0 until fastFood.ingredients.size){
            ingredientText += "${fastFood.ingredients[i]}: "
            ingredientText += "${fastFood.ingredientsCalories[i]} сals\n"
        }
        for (calories in fastFood.ingredientsCalories) {
            calsSum += calories
        }
        ingredientText += "Всего каллорий: ${calsSum}\n\n"
    }

    override fun visit(salad: Salad) {
        ingredientText += "${salad.name}\n"
        for (i in 0 until salad.ingredients.size){
            ingredientText += "${salad.ingredients[i]}: "
            ingredientText += "${salad.ingredientsCalories[i]} сals\n"
        }
        for (calories in salad.ingredientsCalories) {
            calsSum += calories
        }
        ingredientText += "Всего каллорий: ${calsSum}\n\n"
    }
}