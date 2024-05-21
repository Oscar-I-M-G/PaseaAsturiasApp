package com.oimg.paseaasturias.ui.selection.adapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.oimg.paseaasturias.R
import com.oimg.paseaasturias.databinding.ItemSelectionBinding
import com.oimg.paseaasturias.domain.model.SelectionModel
import com.oimg.paseaasturias.ui.detail.SelectionDetailActivity
import com.squareup.picasso.Picasso

class SelectionViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemSelectionBinding.bind(view)
    fun render(selectionInfo: SelectionModel){
        // imagen
        val imageUrl = selectionInfo.Images.firstOrNull()
        if (imageUrl != null) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_map) // placeholder image
                .error(R.drawable.ic_launcher_foreground) // error image
                .resize(200, 200) // resize the image to fit into the ImageView
                .centerCrop() // crop the image
                .into(binding.ivSelection)
        }
        // titulo
        binding.tvName.text = selectionInfo.Nombre
        // Direccion
        val shortAddress =
            "${selectionInfo.Direccion}, ${selectionInfo.Concejo}"

        binding.tvLocation.text = shortAddress

        binding.ivSelection.setOnClickListener {
            moveto(selectionInfo)
        }

        initializeContactIcons(selectionInfo)

    }

    private fun initializeContactIcons(selection:SelectionModel){
        if (!selection.Telefono.isNullOrEmpty()) {
            binding.ivPhone.visibility = View.VISIBLE
            binding.ivPhone.setOnClickListener {
                callIcon(selection)
            }
        } else {
            binding.ivPhone.visibility = View.GONE
        }
        if (!selection.Email.isNullOrEmpty()) {
            binding.ivEmail.visibility = View.VISIBLE
            binding.ivEmail.setOnClickListener {
                emailIcon(selection)
            }
        } else {
            binding.ivEmail.visibility = View.GONE
        }
        if(!selection.Coordenadas.isNullOrEmpty()){
            binding.ivLocationPin.visibility = View.VISIBLE
            binding.ivLocationPin.setOnClickListener {
                addressIcon(selection)
            }
        } else {
            binding.ivLocationPin.visibility = View.GONE
        }
        if (!selection.Web.isNullOrEmpty()) {
            binding.ivWebsite.visibility = View.VISIBLE
            binding.ivWebsite.setOnClickListener {
                webIcon(selection)
            }
        } else {
            binding.ivWebsite.visibility = View.GONE
        }
    }

    private fun webIcon(selection: SelectionModel) {
        val webpage = Uri.parse(selection.Web)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(itemView.context.packageManager) != null) {
            itemView.context.startActivity(intent)
        }
        Log.d("SELECTION", "Selection clicked: website")
    }

    private fun addressIcon(selection: SelectionModel) {
        val gmmIntentUri = Uri.parse("geo:${selection.Coordenadas}?q=${selection.Coordenadas}(Label)")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(itemView.context.packageManager) != null) {
            itemView.context.startActivity(mapIntent)
        }
        Log.d("SELECTION", "Selection clicked: location")
    }

    private fun emailIcon(selection: SelectionModel) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + selection.Email))
        itemView.context.startActivity(intent)
        Log.d("SELECTION", "Selection clicked: email")
    }

    private fun callIcon(selection: SelectionModel){
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + selection.Telefono))
        itemView.context.startActivity(intent)
        Log.d("SELECTION", "Selection clicked: phone")
    }

    private fun moveto(selection: SelectionModel) {
            val intent = Intent(itemView.context, SelectionDetailActivity::class.java)
            intent.putExtra("SELECTION", selection)
            itemView.context.startActivity(intent)
            Log.d("SELECTION", "Selection clicked: ${selection.Nombre}")
    }
}