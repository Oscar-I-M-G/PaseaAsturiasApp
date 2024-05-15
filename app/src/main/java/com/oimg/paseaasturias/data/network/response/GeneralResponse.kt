package com.oimg.paseaasturias.data.network.response

import com.google.gson.annotations.SerializedName
import com.oimg.paseaasturias.data.Utils.splitString
import com.oimg.paseaasturias.domain.model.SelectionModel

/**
 * Clase de data contiene todos los datos necesarios adquiridos
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
    @SerializedName("Facebook") val Facebook:String,
    @SerializedName("Twitter") val Twitter:String,
    @SerializedName("Youtube") val Youtube:String,
    @SerializedName("Pinterest") val Pinterest:String,
    @SerializedName("Instagram") val Instagram:String,
    @SerializedName("Titulo") val Titulo:String,
    @SerializedName("Texto") val Texto:String,
    @SerializedName("ActividadNombre") val ActividadNombre:String,
    @SerializedName("Tarifa") val Tarifa:String,
    @SerializedName("Accesibilidad") val Accesibilidad:String,
    @SerializedName("Coordenadas") val Coordenadas:String,
    @SerializedName("Slide") val Slide:String,
    @SerializedName("SlideTitulo") val SlideTitulo:String,
    @SerializedName("SuplementoMascota") val SuplementoMascota:String,
    @SerializedName("PrecioSuplemento") val PrecioSuplemento:String,
    @SerializedName("LimitacionPeso") val LimitacionPeso:String,
    @SerializedName("AdmitePPP") val AdmitePPP:String, //de las mascotas
    @SerializedName("NumeroMaximo") val NumeroMaximo:String,
    @SerializedName("MaterialNecesario") val MaterialNecesario:String,
    @SerializedName("SuministroMaterial") val SuministroMaterial:String,
    @SerializedName("OtrasMascotas") val OtrasMascotas:String,
    @SerializedName("OtrosAnimales") val OtrosAnimales:String,
    @SerializedName("DetalleNormas") val DetalleNormas:String
){
    // funcion que solo manda cosas especificas MAPEADA
        fun toDomain(): SelectionModel {
            val imageBaseUrl = "https://www.turismoasturias.es" //base url
            val imageUrls = splitString(Slide).map { "$imageBaseUrl$it" }   //map para recorrer la lista de imagenes
            val activities = splitString(ActividadNombre)
            val imagetextos = splitString(SlideTitulo)   //titulos de la imagenes
        return SelectionModel(
                Nombre = Nombre,
                Slide = Slide,
                Zona = Zona,
                Images = imageUrls,
                Concejo = Concejo,
                Direccion = Direccion,
                CP = CP,
                Localidad = Localidad,
                Temporadas = Temporadas,
                FechasDeCierre = FechasDeCierre,
                DireccionSedeAlternativa = DireccionSedeAlternativa,
                Telefono = Telefono,
                Email = Email,
                Web = Web,
                Facebook = Facebook,
                Twitter = Twitter,
                Youtube = Youtube,
                Pinterest = Pinterest,
                Instagram = Instagram,
                Titulo = Titulo,
                Texto = Texto,
                ActividadNombre = ActividadNombre,
                Actividades = activities,
                Tarifa = Tarifa,
                Accesibilidad = Accesibilidad,
                Coordenadas = Coordenadas,
                SlideTitulo = SlideTitulo,
                ImagesText = imagetextos,
                SuplementoMascota = SuplementoMascota,
                PrecioSuplemento = PrecioSuplemento,
                LimitacionPeso = LimitacionPeso,
                AdmitePPP = AdmitePPP,
                NumeroMaximo = NumeroMaximo,
                MaterialNecesario = MaterialNecesario,
                SuministroMaterial = SuministroMaterial,
                OtrasMascotas = OtrasMascotas,
                OtrosAnimales = OtrosAnimales,
                DetalleNormas = DetalleNormas
            )
        }



}
