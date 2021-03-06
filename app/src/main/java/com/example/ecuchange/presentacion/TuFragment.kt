package com.example.ecuchange.presentacion

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.data.database.entidades.UsuarioEntity
import com.example.ecuchange.databinding.FragmentListarBinding
import com.example.ecuchange.databinding.FragmentTuBinding
import com.example.ecuchange.logica.UsuarioLogica
import com.example.ecuchange.uploadImagen
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class TuFragment : Fragment() {
    private lateinit var binding: FragmentTuBinding
    private lateinit var oneUser: UsuarioEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTuBinding.inflate(inflater, container, false)

        var nombreUsuario: String? = this.arguments?.getString("message")
        var usuario: String? = this.arguments?.getString("usuario").toString()
        println("Nombre del usuario"+usuario)

        oneUser = Json.decodeFromString<UsuarioEntity>(this.arguments?.getString("usuario").toString())
        println(oneUser)

        if(oneUser.nombre==null) {
            binding.txtNombrePerfil.setText("Entra o \nRegístrate")
        }else{
            binding.txtNombrePerfil.setText(nombreUsuario)
            if(oneUser.urlImagen==""){
            }else{
                Picasso.get().load(oneUser.urlImagen).into(binding.imagenUsuario)
                binding.botonIrALogin.visibility = View.GONE
            }
            if(oneUser.urlImagen==null){
                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/test01-deca2.appspot.com/o/Products%2Fusuario.jpg?alt=media&token=57cb41e4-ee4c-465e-aa99-30889ffb9916").into(binding.imagenUsuario)
            }
        }

        binding.botonEditarPerfil.setOnClickListener() {
            var intent = Intent(activity, InformationUser::class.java)
            println()
            startActivity(intent)
        }

        binding.botonIrALogin.setOnClickListener() {
            var intent = Intent(activity, LoginActivity::class.java)
            println()
            startActivity(intent)
        }

        binding.botonSubirProducto.setOnClickListener() {
            var intent = Intent(activity, SeleccionarCategoria::class.java)
            println()
            startActivity(intent)
        }

        binding.botonVerMisProductos.setOnClickListener() {
            var intent = Intent(activity, MisProductos::class.java)
            println()
            startActivity(intent)
        }

        binding.botonCambiarPassword.setOnClickListener() {
            var intent = Intent(activity, CambioPassword::class.java)
            println()
            startActivity(intent)
        }

        binding.botonCerrarSesion.setOnClickListener() {
            var intent = Intent(activity, LoginActivity::class.java)
            println()
            startActivity(intent)
        }

        println("IMAGEN "+oneUser.urlImagen)




        return binding.root


    }


}