package com.example.projectdictionary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectdictionary.dataclass.NewsData
import com.example.projectdictionary.databinding.RowBinding

class NewsAdapter(val items:ArrayList<NewsData>):RecyclerView.Adapter<NewsAdapter.MyViewHolder>(){
    interface OnItemClickListener{
        fun OnItemClick(holder: MyViewHolder, view: View, data: NewsData, position: Int)
    }
    var itemClickListener: OnItemClickListener?=null

    inner class MyViewHolder(val binding:RowBinding):RecyclerView.ViewHolder(binding.root){
        init{
            binding.recyclerTextView.setOnClickListener{
                itemClickListener?.OnItemClick(this,it,items[adapterPosition],adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = RowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.recyclerTextView.text = items[position].newstitle
    }

}