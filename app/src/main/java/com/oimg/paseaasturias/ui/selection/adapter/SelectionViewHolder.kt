package com.oimg.paseaasturias.ui.selection.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.oimg.paseaasturias.databinding.ItemSelectionBinding
import com.oimg.paseaasturias.domain.model.SelectionModel

class SelectionViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemSelectionBinding.bind(view)
    fun render(selectionInfo: SelectionModel){
        // imagen

        // titulo
        binding.tvName.text = selectionInfo.Nombre
        // Ciudad
        binding.tvTown.text = selectionInfo.Zona
    }
}