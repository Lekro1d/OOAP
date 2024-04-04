package com.example.clothesfactory.cottonFactory

import com.example.clothesfactory.abstractFactory.ClothFactory
import com.example.clothesfactory.abstractFactory.Dress
import com.example.clothesfactory.abstractFactory.Shirt
import com.example.clothesfactory.abstractFactory.Trousers

class CottonClothFactory: ClothFactory{
    override fun createDress(): Dress {
        return CottonDress()
    }

    override fun createShirt(): Shirt {
        return CottonShirt()
    }

    override fun createTrousers(): Trousers {
        return CottonTrousers()
    }
}