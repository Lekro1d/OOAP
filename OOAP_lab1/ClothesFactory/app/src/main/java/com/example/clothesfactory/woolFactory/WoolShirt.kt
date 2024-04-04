package com.example.clothesfactory.woolFactory

import com.example.clothesfactory.abstractFactory.Shirt

class WoolShirt: Shirt {
    override fun createShirt(count: Int): String {
        return "${count * 7}м шерстяной ткани"
    }
}