package com.example.clothesfactory.silkFactory

import com.example.clothesfactory.abstractFactory.ClothFactory
import com.example.clothesfactory.abstractFactory.Dress
import com.example.clothesfactory.abstractFactory.Shirt
import com.example.clothesfactory.abstractFactory.Trousers

class SilkClothFactory: ClothFactory {
    override fun createDress(): Dress {
        return SilkDress()
    }

    override fun createShirt(): Shirt {
        return SilkShirt()
    }

    override fun createTrousers(): Trousers {
        return SilkTrousers()
    }
}