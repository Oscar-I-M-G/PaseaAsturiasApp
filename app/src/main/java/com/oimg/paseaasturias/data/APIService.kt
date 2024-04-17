package com.oimg.paseaasturias.data

import com.oimg.paseaasturias.domain.TestResponse
import com.oimg.paseaasturias.domain.dataResponse
import retrofit2.Response
import retrofit2.http.GET

//TEST
interface APIService {

    //EmpresasTurismoActivo.json
    @GET("EmpresasTurismoActivo.json")
    suspend fun getMyIDs(): Response<List<dataResponse>>
}

//TEST