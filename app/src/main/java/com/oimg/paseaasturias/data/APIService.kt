package com.oimg.paseaasturias.data

import com.oimg.paseaasturias.domain.TestResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

//TEST
interface APIService {

    //EmpresasTurismoActivo.json
    @GET("EmpresasTurismoActivo.json")
    suspend fun getMyIDs(): Response<List<TestResponse>>
}

//TEST