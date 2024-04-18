package com.oimg.paseaasturias.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.oimg.paseaasturias.data.APIService
import com.oimg.paseaasturias.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.ui.setupWithNavController
import com.oimg.paseaasturias.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // test
    private val BASE_URL = "http://orion.edv.uniovi.es/~arias/json/"
    private val TAG: String = "CHECK_RESPONSE"
    // test

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //  -   Inicializamos el navigation menu de los fragments
        initializeUI()
        // TEST
        //makeApiCall()

        // TEST
    }

    private fun initializeUI() {
        initializeNavigation()
    }

    private fun initializeNavigation() {
        val navigationHost = supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
        navController = navigationHost.navController
        binding.bnv.setupWithNavController(navController)
    }

    // TEST
    private fun makeApiCall() {
        val api = getRetrofit()


        val errorHandler = CoroutineExceptionHandler { _, exception ->
            Log.e(TAG, "Coroutine failed: ${exception.localizedMessage}")
        }

        CoroutineScope(Dispatchers.IO + errorHandler).launch {

            try {

                val response = api.getMyIDs()
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.i(TAG, "API call successful: $responseBody")
                } else {
                    Log.e(TAG, "API call failed: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e(TAG, "Network error: ${e.localizedMessage}")
            }

        }

    }
    // TEST

    private fun getRetrofit(): APIService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
    // TEST

}