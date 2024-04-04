package com.example.adapter.adapter

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ImageAdapter(): Image {
    private var apiKey = "Client-ID v0cQ4Th0VZRX96rP4J6iI1cdYyTrd8d3R8r4ZX5ZL2E"
    private var baseURL = "https://api.unsplash.com/photos/random"

    override fun getPhoto(callback: (AppImage) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(baseURL)
            .addHeader("Accept-Version", "v1")
            .addHeader("Authorization", apiKey)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Ошибка подключения: $e")
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    throw IOException(
                        "Запрос к серверу не был успешен: ${response.code} ${response.message}"
                    )
                }
                val jsonString = response.body!!.string()

                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val jsonAdapterResponse = moshi.adapter(ResponseJSON::class.java)
                val jsonResponse = jsonAdapterResponse.fromJson(jsonString)

                val appImage = AppImage().apply {
                    url = jsonResponse!!.urls.regular
                    author = jsonResponse.user.name
                    createdImage = jsonResponse.createdAt
                    likes = jsonResponse.likes
                }

                callback(appImage)
            }
        })
    }
}