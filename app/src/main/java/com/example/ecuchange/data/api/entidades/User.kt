package com.example.ecuchange.data.api.entidades

import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.data.database.entidades.UsuarioEntity

data class User(
    val __v: Int,
    val _id: String,
    val apellido: String,
    val correo: String,
    val direccion: String,
    val nombre: String,
    val password: String,
    val user: String,
    val telefono: String,
    val urlImagen: String,
)

fun User.toUsuariosEntity() = UsuarioEntity(_id, user, password, correo)

fun User.toOneUserEntity() = UsuarioEntity(_id, user, password, correo, nombre, apellido, direccion,telefono,urlImagen)

fun User.toLiteOneUserEntity() =  UsuarioEntity(_id, user, password, correo, telefono)