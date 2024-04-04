package com.example.adapter.adapter

interface Image {
    fun getPhoto(callback: (AppImage) -> Unit)
}