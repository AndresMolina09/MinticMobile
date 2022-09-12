package com.example.aplicacion_4b_g7

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacion_4b_g7.databinding.ItemServiceBinding

class ServiceAdapter(var list: List<ServiceModel>): RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    var listener: OnServiceClickListener? = null

    class ViewHolder(val view: ItemServiceBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemServiceBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        println("aqui")
        println(holder.view.itemServiceIcon.setImageResource(item.icon.toInt()))
        holder.view.itemServiceTitle.text = item.title
        holder.view.itemServiceDescripcion.text = item.description
        holder.view.itemServiceIcon.setImageResource(item.icon.toInt())
        holder.view.root.setOnClickListener{
            listener?.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}