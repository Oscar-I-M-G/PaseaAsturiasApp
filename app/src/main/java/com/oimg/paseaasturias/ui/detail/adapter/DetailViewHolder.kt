package com.oimg.paseaasturias.ui.detail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.oimg.paseaasturias.databinding.ItemDetailBinding
import com.oimg.paseaasturias.domain.model.DetailModel
import com.squareup.picasso.Picasso

class DetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemDetailBinding.bind(view)

    fun render(detailModel: DetailModel) {

        if (!detailModel.images.isNullOrEmpty()){
            Picasso.get()
                .load(detailModel.images)
                .into(binding.ivItemDetail)
        }
        binding.tvItemDetail.text = detailModel.slideTitulo

    }
}