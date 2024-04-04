package com.example.clothesfactory.silkFactory

import com.example.clothesfactory.abstractFactory.Dress

class SilkDress: Dress {
    override fun createDress(count: Int): String {
        return "${count * 11}м шелковой ткани"
    }
}