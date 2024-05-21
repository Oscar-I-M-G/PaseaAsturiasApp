package com.oimg.paseaasturias.data.network

import com.oimg.paseaasturias.data.network.response.GeneralResponse
import retrofit2.http.GET

/**
 * Interfaz que conecta a "http://orion.edv.uniovi.es/~arias/json/EmpresasTurismoActivo.json"
 * */
interface PaseaAsturiasAPIService {
    @GET("EmpresasTurismoActivo.json")
    // funcion suspend ejecutar en una corutina
    suspend fun getMyAPI(): List<GeneralResponse>
}