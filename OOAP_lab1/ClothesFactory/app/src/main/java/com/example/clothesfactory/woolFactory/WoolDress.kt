package com.example.clothesfactory.woolFactory

import com.example.clothesfactory.abstractFactory.Dress

class WoolDress: Dress {
    override fun createDress(count: Int): String {
        return "${count * 15}м шерстяной ткани"
    }
}