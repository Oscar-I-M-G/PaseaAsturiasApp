package com.oimg.paseaasturias.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.oimg.paseaasturias.R
import com.oimg.paseaasturias.databinding.ActivitySelectionDetailBinding
import com.oimg.paseaasturias.databinding.FragmentSelectionBinding
import com.oimg.paseaasturias.domain.model.DetailModel
import com.oimg.paseaasturias.domain.model.SelectionModel
import com.oimg.paseaasturias.ui.detail.adapter.DetailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectionDetailActivity : AppCompatActivity() {

    //  -   -   -   -   -   Binding -   -   -   -   -   -
    private lateinit var binding : ActivitySelectionDetailBinding
    //  -   -   -   -   -   -   -   -   -   -   -   -   -

    //  -   -   -   -   -  Adapter  -   -   -   -   -   -
    private lateinit var adapter: DetailAdapter
    //  -   -   -   -   -   -   -   -   -   -   -   -   -
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // inicializamos la UI
        initializeUI()
    }

    private fun initializeUI() {
        //utilizamos getSerializableExtra para obtener el objeto seleccionado
        val selection = intent.getSerializableExtra("SELECTION") as SelectionModel
        binding.tvDetail.text = selection.Nombre
        initializeSocialIcons()
        initializeImages()
    }
    private fun initializeSocialIcons(){
        val selection = intent.getSerializableExtra("SELECTION") as SelectionModel
        // comprobamos si la url de la red social no es nula o vacia
        // si no es nula o vacia mostramos el icono de la red social y le damos su link
        if (!selection.Facebook.isNullOrEmpty()) {
            binding.ivFacebook.visibility = View.VISIBLE
            binding.ivFacebook.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selection.Facebook))
                startActivity(intent)
            }
        } else{
            binding.ivFacebook.visibility = View.GONE
        }
        if (!selection.Twitter.isNullOrEmpty()) {
            binding.ivTwitter.visibility = View.VISIBLE
            binding.ivTwitter.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selection.Twitter))
                startActivity(intent)
            }
        } else{
            binding.ivTwitter.visibility = View.GONE
        }
        if (!selection.Instagram.isNullOrEmpty()) {
            binding.ivInstagram.visibility = View.VISIBLE
            binding.ivInstagram.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selection.Instagram))
                startActivity(intent)
            }
        } else{
            binding.ivInstagram.visibility = View.GONE
        }
        if (!selection.Pinterest.isNullOrEmpty()) {
            binding.ivPinterest.visibility = View.VISIBLE
            binding.ivPinterest.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selection.Pinterest))
                startActivity(intent)
            }
        } else{
            binding.ivPinterest.visibility = View.GONE
        }
        if (!selection.Youtube.isNullOrEmpty()) {
            binding.ivYoutube.visibility = View.VISIBLE
            binding.ivYoutube.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selection.Youtube))
                startActivity(intent)
            }
        } else{
            binding.ivYoutube.visibility = View.GONE
        }
    }

    private fun initializeImages() {
        val selection = intent.getSerializableExtra("SELECTION") as SelectionModel
        adapter = DetailAdapter()
        binding.rvImages.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvImages.adapter = adapter

        // creamos listas de imagenes y textos
        val detailModelList = ArrayList<DetailModel>()

        selection.Images?.let {
            for (image in it) {
                detailModelList.add(DetailModel(image,""))
            }

        }
        selection.ImagesText?.let {
            for (imageText in it) {
                detailModelList.add(DetailModel("",imageText))
            }
        }

        adapter.updateList(detailModelList)
    }
}