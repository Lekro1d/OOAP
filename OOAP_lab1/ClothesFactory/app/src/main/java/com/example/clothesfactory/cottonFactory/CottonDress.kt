package com.example.clothesfactory.cottonFactory

import com.example.clothesfactory.abstractFactory.Dress

class CottonDress: Dress {
    override fun createDress(count: Int): String {
        return "${count * 9}м хлопковой ткани"
    }
}