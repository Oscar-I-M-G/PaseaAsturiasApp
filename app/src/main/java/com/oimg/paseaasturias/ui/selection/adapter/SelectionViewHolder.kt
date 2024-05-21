package com.oimg.paseaasturias.ui.selection.adapter

import android.content.Intent
import android.util.Log
import android.view.View
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
        // Ciudad
        val shortAddress =
            "${selectionInfo.Direccion}, ${selectionInfo.Concejo}"

        binding.tvLocation.text = shortAddress

        binding.ivSelection.setOnClickListener {
            moveto(selectionInfo)
        }

    }
    private fun moveto(selection: SelectionModel) {
            val intent = Intent(itemView.context, SelectionDetailActivity::class.java)
            intent.putExtra("SELECTION", selection)
            itemView.context.startActivity(intent)
            Log.d("SELECTION", "Selection clicked: ${selection.Nombre}")
    }
}