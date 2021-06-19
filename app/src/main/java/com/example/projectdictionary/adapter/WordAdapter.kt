package com.example.projectdictionary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectdictionary.R
import com.example.projectdictionary.dataclass.WordData

class WordAdapter (val data: ArrayList<WordData>) : RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val wordView: TextView = view.findViewById(R.id.myWordword)
        val meaningView: TextView = view.findViewById(R.id.myWordmeaning)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.myword_row,parent,false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: WordAdapter.ViewHolder, position: Int) {
        val item = data[position].word
        val item2 = data[position].meaning
        holder.wordView.text = item
        holder.meaningView.text = item2
    }
    interface OnItemClickListener{
        fun OnItemClick(holder: ViewHolder, view:View, data:WordData, position: Int)
    }
    var itemClickListener:OnItemClickListener?=null
}