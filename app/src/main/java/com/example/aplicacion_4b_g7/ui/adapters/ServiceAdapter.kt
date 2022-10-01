package com.example.aplicacion_4b_g7.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_4b_g7.data.models.DoctorModel
import com.example.aplicacion_4b_g7.interfaces.OnServiceClickListener
import com.example.aplicacion_4b_g7.data.models.ServiceModel
import com.example.aplicacion_4b_g7.databinding.ItemServiceBinding

class ServiceAdapter(var list: MutableList<ServiceModel>): RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    var listener: OnServiceClickListener? = null

    class ViewHolder(val view: ItemServiceBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemServiceBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.view.itemServiceTitle.text = item.title
        holder.view.itemServiceDescripcion.text = item.description
        //holder.view.itemServiceIcon.setImageResource(item.icon.toInt())
        Glide.with(holder.view.root).load(item.icon).into(holder.view.itemServiceIcon)
        holder.view.root.setOnClickListener{
            listener?.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun changeDataSet(newList: List<ServiceModel>){
        while (list.size > 0){
            list.removeAt(0)
            notifyItemRemoved(0)
        }
        newList.forEach{
            this.list.add(it)
            notifyItemInserted(this.list.size - 1)
        }
    }
}