package com.example.clothesfactory.cottonFactory

import com.example.clothesfactory.abstractFactory.Shirt

class CottonShirt: Shirt {
    override fun createShirt(count: Int): String {
        return "${count * 5}м хлопковой ткани"
    }
}