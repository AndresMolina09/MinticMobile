package com.example.aplicacion_4b_g7.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_4b_g7.data.models.DoctorModel
import com.example.aplicacion_4b_g7.interfaces.OnDoctorClickListener
import com.example.aplicacion_4b_g7.databinding.ItemDoctorBinding

class DoctorAdapter(var list: MutableList<DoctorModel>): RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {

    var listener: OnDoctorClickListener? = null

    class ViewHolder(val view: ItemDoctorBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemDoctorBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.view.itemDoctorTitle.text = item.speciality
        holder.view.itemDoctorName.text = item.name
        holder.view.itemDoctorSummary.text = item.caption
        holder.view.itemDoctorRating.rating = (item.star/5.0).toFloat()
        //holder.view.itemDoctorIcon.setImageResource(item.image.toInt())
        Glide.with(holder.view.root).load(item.image).centerCrop().into(holder.view.itemDoctorIcon)
        holder.view.root.setOnClickListener{
            listener?.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun changeDataSet(newList: List<DoctorModel>){
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