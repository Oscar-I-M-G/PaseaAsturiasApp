package com.oimg.paseaasturias.ui.pursuits.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.oimg.paseaasturias.databinding.ItemPursuitBinding
import com.oimg.paseaasturias.domain.model.PursuitInfo

class PursuitsViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemPursuitBinding.bind(view)
    fun render(pursuitInfo: PursuitInfo){
        // Imagen
        binding.ivPursuit.setImageResource(pursuitInfo.image)

        val context = binding.tvPursuit.context
        binding.tvPursuit.text = context.getString(pursuitInfo.name)

    }
}