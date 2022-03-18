package com.example.ecuchange.presentacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.R
import com.example.ecuchange.databinding.ActivityItemBinding
import com.example.ecuchange.databinding.ActivityLoginBinding
import com.example.ecuchange.entities.Products
import com.example.ecuchange.logica.ProductsLogica
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ItemActivity : AppCompatActivity() {

        private lateinit var binding: ActivityItemBinding

        private var fav: Boolean = false
        var newsObtain: ArticlesEntity? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityItemBinding.inflate(layoutInflater)
            setContentView(binding.root)

            var n: ArticlesEntity? = null
            intent.extras?.let{
               newsObtain = Json.decodeFromString<ArticlesEntity>(it.getString("producto").toString())
                println(newsObtain)
            }

            if (newsObtain != null) {
                loadNews(newsObtain!!)
            }




            }

    private fun loadNews(articlesEntity: ArticlesEntity) {
        binding.tituloItem.text = articlesEntity.titulo
        binding.descripcionItem.text = articlesEntity.descripcion
        Picasso.get().load(articlesEntity.imagen).into(binding.imagenProducto)
        /*
        lifecycleScope.launch(Dispatchers.Main) {
            fav = withContext(Dispatchers.IO) { ProductsLogica().checkIsSaved(articlesEntity.id.toString()) }
            if (fav) {
                binding.floatingActionButtonItem.setImageResource(R.drawable.heart24)
            }
        }*/
    }



        }
