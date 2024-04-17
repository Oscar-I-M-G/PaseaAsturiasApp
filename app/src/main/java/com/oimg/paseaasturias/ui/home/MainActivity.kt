package com.oimg.paseaasturias.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat.CallStyle.CallType
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.oimg.paseaasturias.data.APIService
import com.oimg.paseaasturias.databinding.ActivityMainBinding
import com.oimg.paseaasturias.domain.TestResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException

class MainActivity : AppCompatActivity() {

    // test
    private val BASE_URL = "http://orion.edv.uniovi.es/~arias/json/"
    private val TAG: String = "CHECK_RESPONSE"
    // test

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // TEST

        makeApiCall()

        // TEST
    }

    // TEST
    private fun makeApiCall() {
        val api = getRetrofit()


        val errorHandler = CoroutineExceptionHandler{_,exception ->
            Log.e(TAG, "Coroutine failed: ${exception.localizedMessage}")
        }

        CoroutineScope(Dispatchers.IO + errorHandler).launch {

            try {

                val response = api.getMyIDs()
                if (response.isSuccessful){
                    val responseBody = response.body()
                    Log.i(TAG, "API call successful: $responseBody")
                }else{
                    Log.e(TAG,"API call failed: ${response.code()}")
                }
            } catch (e: IOException){
                Log.e(TAG,"Network error: ${e.localizedMessage}")
            }

        }

    }


    private fun getRetrofit(): APIService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    // TEST

}