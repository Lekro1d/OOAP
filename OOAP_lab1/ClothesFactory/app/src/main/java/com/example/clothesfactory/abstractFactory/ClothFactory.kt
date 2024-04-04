package com.example.clothesfactory.abstractFactory

interface ClothFactory {
    fun createDress(): Dress
    fun createShirt(): Shirt
    fun createTrousers(): Trousers
}