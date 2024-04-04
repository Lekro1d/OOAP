package com.example.clothesfactory.silkFactory

import com.example.clothesfactory.abstractFactory.Shirt

class SilkShirt: Shirt {
    override fun createShirt(count: Int): String {
        return "${count * 4}м шелковой ткани"
    }
}