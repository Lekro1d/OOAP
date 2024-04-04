package com.example.adapter

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import com.example.adapter.adapter.ImageAdapter
import com.example.adapter.databinding.ActivityClientBinding
import com.example.adapter.notAdapter.ImageNotAdapter

class ClientActivity : AppCompatActivity() {
    lateinit var binding: ActivityClientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageView = binding.imageView
        val adapter = ImageAdapter()

        binding.btnSendPattern.setOnClickListener{
            adapter.getPhoto() { appImage ->
                runOnUiThread {
                    val imageRequest = ImageRequest(appImage.url, {
                        imageView.setImageBitmap(it)
                    }, 0, 0, null, Bitmap.Config.ARGB_8888, {
                        Toast.makeText(this, "Изображение не загружено", Toast.LENGTH_SHORT).show()
                    })
                    Volley.newRequestQueue(this).add(imageRequest)

                    binding.textAuthor.text = "Автор: ${appImage.author}"
                    binding.textCreatedImage.text = "Дата публикации: ${appImage.createdImage}"
                    binding.textLikes.text = "Количество лайков: ${appImage.likes}"
                }
            }
        }

        val imageNotAdapter = ImageNotAdapter()

        binding.btnSend.setOnClickListener {
            imageNotAdapter.getPhoto {
                runOnUiThread {
                    val imageRequest = ImageRequest(it.urls.regular, {
                        imageView.setImageBitmap(it)
                    }, 0, 0, null, Bitmap.Config.ARGB_8888, {
                        Toast.makeText(this, "Изображение не загружено", Toast.LENGTH_SHORT).show()
                    })
                    Volley.newRequestQueue(this).add(imageRequest)

                    binding.textAuthor.text = "Автор: ${it.user.name}"
                    binding.textCreatedImage.text = "Дата публикации: ${it.createdAt}"
                    binding.textLikes.text = "Количество лайков: ${it.likes}"
                }
            }
        }
    }
}