package com.oimg.paseaasturias.ui.pursuits.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oimg.paseaasturias.R
import com.oimg.paseaasturias.domain.model.PursuitInfo

class PursuitsAdapter(private var pursuitList: List<PursuitInfo> = emptyList()) :
    RecyclerView.Adapter<PursuitsViewHolder>() {

        fun updateList(list: List<PursuitInfo>){
            pursuitList = list
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PursuitsViewHolder {
        return PursuitsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pursuit,parent,false)
        )
    }

    override fun getItemCount() = pursuitList.size

    override fun onBindViewHolder(holder: PursuitsViewHolder, position: Int) {
        holder.render(pursuitList[position])
    }
}