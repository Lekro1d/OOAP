package com.example.clothesfactory.woolFactory

import com.example.clothesfactory.abstractFactory.ClothFactory
import com.example.clothesfactory.abstractFactory.Dress
import com.example.clothesfactory.abstractFactory.Shirt
import com.example.clothesfactory.abstractFactory.Trousers

class WoolClothFactory: ClothFactory {
    override fun createDress(): Dress {
        return WoolDress()
    }

    override fun createShirt(): Shirt {
        return WoolShirt()
    }

    override fun createTrousers(): Trousers {
        return WoolTrousers()
    }
}