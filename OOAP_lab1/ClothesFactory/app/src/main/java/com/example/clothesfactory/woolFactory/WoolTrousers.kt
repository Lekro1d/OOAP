package com.example.clothesfactory.woolFactory

import com.example.clothesfactory.abstractFactory.Trousers

class WoolTrousers: Trousers {
    override fun createTrousers(count: Int): String {
        return "${count * 10}м шерстяной ткани"
    }
}