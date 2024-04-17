package com.oimg.paseaasturias.domain

import com.google.gson.annotations.SerializedName

//TEST
data class TestResponse (
    @SerializedName("CodigoDGT") val codigoDGT: String,
    @SerializedName("Nombre") val nombre: String
)
//TEST
