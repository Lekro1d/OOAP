package com.example.visitor.visitors

import com.example.visitor.items.FastFood
import com.example.visitor.items.Salad

class Rating: Visitor {
    var textRating = ""
    override fun visit(fastFood: FastFood) {
        textRating += "Рейтинг ${fastFood.name}: ${fastFood.rating}\n\n"
    }

    override fun visit(salad: Salad) {
        textRating += "Рейтинг ${salad.name}: ${salad.rating}\n\n"
    }
}