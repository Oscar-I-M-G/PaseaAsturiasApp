package com.oimg.paseaasturias.ui.selection.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oimg.paseaasturias.R
import com.oimg.paseaasturias.domain.model.SelectionModel

class SelectionAdapter(private var selectionList: List<SelectionModel> = emptyList()) :
    RecyclerView.Adapter<SelectionViewHolder>() {
    fun updateList(list: List<SelectionModel>) {
        selectionList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectionViewHolder {
        return SelectionViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_selection, parent, false)
        )
    }

    override fun getItemCount() = selectionList.size

    override fun onBindViewHolder(holder: SelectionViewHolder, position: Int) {
        holder.render(selectionList[position])
    }
}