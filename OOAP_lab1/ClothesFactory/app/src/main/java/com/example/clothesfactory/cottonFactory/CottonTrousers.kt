package com.example.clothesfactory.cottonFactory

import com.example.clothesfactory.abstractFactory.Trousers

class CottonTrousers: Trousers {
    override fun createTrousers(count: Int): String {
        return "${count * 7}м хлопковой ткани"
    }
}