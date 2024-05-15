package com.oimg.paseaasturias.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oimg.paseaasturias.R
import com.oimg.paseaasturias.domain.model.DetailModel

class DetailAdapter(private var detailList:List<DetailModel> = emptyList()) :
    RecyclerView.Adapter<DetailViewHolder>() {

fun updateList(list: List<DetailModel>){
    detailList = list
    notifyDataSetChanged()
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
    return DetailViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_detail,parent,false)
    )
}

override fun getItemCount() = detailList.size
override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
    holder.render(detailList[position])
}


}