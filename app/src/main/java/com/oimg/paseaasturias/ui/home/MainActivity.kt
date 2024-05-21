package com.oimg.paseaasturias.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.oimg.paseaasturias.R
import com.oimg.paseaasturias.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //  -   Inicializamos el navigation menu de los fragments
        initializeUI()
    }

    private fun initializeUI() {
        initializeNavigation()
    }

    private fun initializeNavigation() {
        val navigationHost = supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
        navController = navigationHost.navController
        binding.bnv.setupWithNavController(navController)
    }




}