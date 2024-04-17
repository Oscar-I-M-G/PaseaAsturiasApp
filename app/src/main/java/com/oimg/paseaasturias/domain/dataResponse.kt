package com.oimg.paseaasturias.domain

import com.google.gson.annotations.SerializedName

data class dataResponse(
    @SerializedName("Nombre") val Nombre:String,
    @SerializedName("Zona") val Zona:String,
    @SerializedName("Slide") val Slide:String
)
