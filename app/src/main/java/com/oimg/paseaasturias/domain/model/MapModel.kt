package com.oimg.paseaasturias.domain.model

import java.io.Serializable

data class MapModel(
    val Name: String,
    val Direccion: String,
    val Telefono: String,
    val Web: String,
    val Email: String,
    val Picture: String,
    val CoordenadasLatLon: List<Double>
) : Serializable