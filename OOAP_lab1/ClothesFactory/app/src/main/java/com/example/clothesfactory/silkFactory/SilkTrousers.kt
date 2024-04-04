package com.example.clothesfactory.silkFactory

import com.example.clothesfactory.abstractFactory.Trousers

class SilkTrousers: Trousers {
    override fun createTrousers(count: Int): String {
        return "${count * 8}м шелковой ткани"
    }
}