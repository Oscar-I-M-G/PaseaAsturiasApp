package com.oimg.paseaasturias.domain.model

import java.io.Serializable

data class SelectionModel(
    val Nombre:String,
    val Concejo:String,
    val Zona:String,
    val Direccion:String,
    val CP:Int,
    val Localidad:String,
    val Temporadas:String,
    val FechasDeCierre:String,
    val DireccionSedeAlternativa:String,
    val Telefono:String,
    val Email: String,
    val Web:String,
    val Facebook:String,
    val Twitter:String,
    val Youtube:String,
    val Pinterest:String,
    val Instagram:String,
    val Titulo:String,
    val Texto:String,
    val ActividadNombre:String,
    val Actividades:List<String>,
    val Tarifa:String,
    val Accesibilidad:String,
    val Coordenadas:String,
    val Slide: String,
    val Images: List<String>,
    val SlideTitulo:String,
    val ImagesText: List<String>,
    val SuplementoMascota:String,
    val PrecioSuplemento:String?,//puede ser nulo
    val LimitacionPeso:String?,//puede ser nulo
    val AdmitePPP:String, //de las mascotas
    val NumeroMaximo:String,
    val MaterialNecesario:String,
    val SuministroMaterial:String,
    val OtrasMascotas:String,
    val OtrosAnimales:String,
    val DetalleNormas:String
) : Serializable
