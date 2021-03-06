package com.example.ecuchange.casosDeUso
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.data.api.RetrofitAPI
import com.example.ecuchange.data.api.entidades.toArticlesEntity
import com.example.ecuchange.data.api.entidades.toCategoryEntity
import com.example.ecuchange.data.api.service.ArticulosService
import com.example.ecuchange.data.database.entidades.CategoryEntity
import com.example.ecuchange.entities.Products
import com.example.ecuchange.utils.EcuChange

import retrofit2.create

class ProductoUserCase {

//private val productosList = listOf<Products>(
//    Products("1",
//        "Laptop Acer 7600",
//        "Ram 8gb Color blanca",
//        "https://1.bp.blogspot.com/--c3KYdSz9Sg/YUlMsHlvW1I/AAAAAAAAVVo/n8YPk9NzUYYBSj4kBHbOV4Mw9vjmS7GtQCLcBGAsYHQ/s198/66591687-vector-logo-plana-de-taxi-aislado-sobre-fondo-blanco-la-cara-del-coche-icono-de-la-silueta-modelo-de.jpg"
//    ),
//    Products("2",
//        "Xbox Series x",
//        "La ultima consola de videojuegos",
//        "https://1.bp.blogspot.com/-9hfiTS1GRGw/YUjqpH5LX1I/AAAAAAAAVVE/TBLYr0reFbMlF079vOMZltrJ6txLAyBiwCLcBGAsYHQ/s64/taxi.png",
//    ),
//    Products("3",
//        "Motoneta Gp Zuzuki",
//        "100 caballos de fuerza Color blanca",
//        "http://a.fsdn.com/sd/topics/graphics_64.png"
//    )
//
//
//    )

    suspend fun getAllCategories (): List<CategoryEntity> {
        var resp: List<CategoryEntity> = ArrayList<CategoryEntity>()

        val service = RetrofitAPI.getArticulosApi().create(ArticulosService::class.java)
        val call = service.getAllCategories("categorias")

        resp = if (call.isSuccessful) {
            return call.body()!!.category.map {
                it.toCategoryEntity()
            }
        } else (ArrayList<CategoryEntity>())
            return resp
    }

    suspend fun getAllProducts(category: String): List<ArticlesEntity> {
        val url: String
        if (category.isNotEmpty()) {
            url = "filtroCategoria/$category"
        } else {
            url = "articulos"
        }
        var resp: List<ArticlesEntity> = ArrayList<ArticlesEntity>()

        val service = RetrofitAPI.getArticulosApi().create(ArticulosService::class.java)
        val call = service.getAllArticulosbyCategoria(url)

        resp = if (call.isSuccessful) {
            return call.body()!!.articles.map {
                it.toArticlesEntity()
            }
        } else (ArrayList<ArticlesEntity>())
        return resp
    }

    suspend fun getAllProductsbyUser(idUser: String): List<ArticlesEntity> {
        val url: String
        if (idUser.isNotEmpty()) {
            url = "filtroUsuario/$idUser"
        } else {
            url = "articulos"
        }
        var resp: List<ArticlesEntity> = ArrayList<ArticlesEntity>()

        val service = RetrofitAPI.getArticulosApi().create(ArticulosService::class.java)
        val call = service.getAllArticulosbyUser(url)

        resp = if (call.isSuccessful) {
            return call.body()!!.articles.map {
                it.toArticlesEntity()
            }
        } else (ArrayList<ArticlesEntity>())
        return resp
    }


    suspend fun getOneProduct(id: String): ArticlesEntity {
        val service = RetrofitAPI.getOneArticulosApi().create(ArticulosService::class.java)
        val call = service.getOneArticulo(id)
        val articulo1 : ArticlesEntity
        if (call.isSuccessful) {
            val body = call.body()
            articulo1 = body!!.toArticlesEntity()
            return articulo1
        } else
        return ArticlesEntity("","","","",50, "")

    }

    suspend fun saveNewFavNews(articulo: ArticlesEntity) {
        EcuChange.getDatabase().articulosDao().insertProducts(articulo)
    }

    suspend fun deleteNewFavNews(articulo: ArticlesEntity) {
        EcuChange.getDatabase().articulosDao().deleteNewsById(articulo.id.toString())
    }

    suspend fun getOneFavoriteProduct(id: String) : ArticlesEntity{
        return EcuChange.getDatabase().articulosDao().getProductsById(id)
    }

    suspend fun getFavoritesProducts(): List<ArticlesEntity> {
        val db = EcuChange.getDatabase()
        return db.articulosDao().getAllProducts()
    }

}



