package com.oimg.paseaasturias.data

import android.util.Log
import com.oimg.paseaasturias.data.network.PaseaAsturiasAPIService
import com.oimg.paseaasturias.domain.Repository
import com.oimg.paseaasturias.domain.model.MapModel
import com.oimg.paseaasturias.domain.model.SelectionModel
import javax.inject.Inject


// Extiende a Repository de nuestr capa Domain
class RepositoryImpl @Inject constructor(private val apiService: PaseaAsturiasAPIService) :
    Repository {
    //Peticion a retrofit
    override suspend fun getPreviewData(): List<SelectionModel>? {
        return try {
            val response = apiService.getMyAPI()
            Log.i("API_SERVICE", "It works SELECTION")
            response.map {
                it.toDomain()
                }
        } catch (e: Exception) {
        Log.e("API_SERVICE", "An error occurred: ${e.message} SELECTION")
            null
        }
    }

    override suspend fun getMapData(): List<MapModel>? {
        return try {
            val response = apiService.getMyAPI()
            Log.i("API_SERVICE", "It works MAPS")
            response.map {
                it.toDomainMap()
            }
        } catch (e: Exception) {
            Log.e("API_SERVICE", "An error occurred: ${e.message} MAPS")
            null
        }
    }


}

