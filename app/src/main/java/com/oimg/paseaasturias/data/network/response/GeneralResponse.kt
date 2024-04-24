package com.oimg.paseaasturias.data.network.response

import com.google.gson.annotations.SerializedName
import com.oimg.paseaasturias.domain.model.SelectionModel

/**
 * Clase de data contiene todo los datos necesarios adquiridos
 * del API y se divide en
 * funciones para mandar cosas especificas
 */
data class GeneralResponse(
    @SerializedName("CodigoDGT") val CodigoDGT:String,
    @SerializedName("Nombre") val Nombre:String,
    @SerializedName("Concejo") val Concejo:String,
    @SerializedName("Zona") val Zona:String,
    @SerializedName("Direccion") val Direccion:String,
    @SerializedName("CP") val CP:Int,
    @SerializedName("Localidad") val Localidad:String,
    @SerializedName("Temporadas") val Temporadas:String,
    @SerializedName("FechasDeCierre") val FechasDeCierre:String,
    @SerializedName("DireccionSedeAlternativa") val DireccionSedeAlternativa:String,
    @SerializedName("Telefono") val Telefono:String,
    @SerializedName("Email") val Email: String,
    @SerializedName("Web") val Web:String,
    //falto FACEBOOK a ArchivoTitulo
    @SerializedName("Titulo") val Titulo:String,
    //falto Texto a Accesibilidad
    @SerializedName("Coordenadas") val Coordenadas:String,
    @SerializedName("Slide") val Slide:String
    // falta mas pero por el momento solo esto
){
    // funcion que solo manda cosas especificas MAPEADA
        fun toDomain(): SelectionModel {
            return SelectionModel(
                Nombre = Nombre,
                Slide = Slide,
                Zona = Zona
            )
        }

}
