package com.oimg.paseaasturias.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oimg.paseaasturias.databinding.ActivitySelectionDetailBinding
import com.oimg.paseaasturias.domain.model.DetailModel
import com.oimg.paseaasturias.domain.model.SelectionModel
import com.oimg.paseaasturias.ui.detail.adapter.DetailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectionDetailActivity : AppCompatActivity() {

    //  -   -   -   -   -   Binding -   -   -   -   -   -
    private lateinit var binding: ActivitySelectionDetailBinding
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
        initializeContactInfo()
        initializeAdress()
        initializeAdress()
        initializeTitulo()
        initializeActivities()
    }

    private fun initializeActivities() {

    }

    private fun initializeTitulo() {
        val selection = intent.getSerializableExtra("SELECTION") as SelectionModel
        binding.tvTitulo.text = selection.Titulo
    }

    private fun initializeAdress() {
        val selection = intent.getSerializableExtra("SELECTION") as SelectionModel
         val address =
                "${selection.Direccion}, ${selection.Concejo}, ${selection.Localidad}, ${selection.Zona}, ${selection.CP}"
            binding.tvAddress.text = address
            binding.ivAddress.setOnClickListener {
                val coordinates = selection.Coordenadas.split(",").map { it.trim().toDouble() }
                val geoUri = Uri.parse("geo:${coordinates[0]},${coordinates[1]}")
                val mapIntent = Intent(Intent.ACTION_VIEW, geoUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }

    }

    private fun initializeContactInfo() {
        val selection = intent.getSerializableExtra("SELECTION") as SelectionModel
        // comprobamos si el telefono no es nulo o vacio
        // si no es nulo o vacio mostramos el icono de telefono y le damos su link
        if (!selection.Telefono.isNullOrEmpty()) {
            binding.ivPhone.visibility = View.VISIBLE
            binding.tvPhone.text = selection.Telefono
            binding.llPhone.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + selection.Telefono))
                startActivity(intent)
            }
        } else {
            binding.llPhone.visibility = View.GONE

        }
        // comprobamos si el email no es nulo o vacio
        // si no es nulo o vacio mostramos el icono de email y le damos su link
        if (!selection.Email.isNullOrEmpty()) {
            binding.ivEmail.visibility = View.VISIBLE
            binding.tvEmail.text = selection.Email
            binding.llEmail.setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + selection.Email))
                startActivity(intent)
            }
        } else {
            binding.llEmail.visibility = View.GONE
        }
        // comprobamos si la web no es nula o vacia
        // si no es nula o vacia mostramos el icono de web y le damos su link
        if (!selection.Web.isNullOrEmpty()) {
            binding.ivWebsite.visibility = View.VISIBLE
            binding.tvWebsite.text = selection.Web
            binding.llWebsite.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selection.Web))
                startActivity(intent)
            }
        } else {
            binding.llWebsite.visibility = View.GONE
        }
    }

    private fun initializeSocialIcons() {
        val selection = intent.getSerializableExtra("SELECTION") as SelectionModel
        // comprobamos si la url de la red social no es nula o vacia
        // si no es nula o vacia mostramos el icono de la red social y le damos su link
        if (!selection.Facebook.isNullOrEmpty()) {
            binding.ivFacebook.visibility = View.VISIBLE
            binding.ivFacebook.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selection.Facebook))
                startActivity(intent)
            }
        } else {
            binding.ivFacebook.visibility = View.GONE
        }
        if (!selection.Twitter.isNullOrEmpty()) {
            binding.ivTwitter.visibility = View.VISIBLE
            binding.ivTwitter.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selection.Twitter))
                startActivity(intent)
            }
        } else {
            binding.ivTwitter.visibility = View.GONE
        }
        if (!selection.Instagram.isNullOrEmpty()) {
            binding.ivInstagram.visibility = View.VISIBLE
            binding.ivInstagram.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selection.Instagram))
                startActivity(intent)
            }
        } else {
            binding.ivInstagram.visibility = View.GONE
        }
        if (!selection.Pinterest.isNullOrEmpty()) {
            binding.ivPinterest.visibility = View.VISIBLE
            binding.ivPinterest.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selection.Pinterest))
                startActivity(intent)
            }
        } else {
            binding.ivPinterest.visibility = View.GONE
        }
        if (!selection.Youtube.isNullOrEmpty()) {
            binding.ivYoutube.visibility = View.VISIBLE
            binding.ivYoutube.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selection.Youtube))
                startActivity(intent)
            }
        } else {
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
                detailModelList.add(DetailModel(image, ""))
            }

        }
        selection.ImagesText?.let {
            for (imageText in it) {
                detailModelList.add(DetailModel("", imageText))
            }
        }

        adapter.updateList(detailModelList)
    }
}