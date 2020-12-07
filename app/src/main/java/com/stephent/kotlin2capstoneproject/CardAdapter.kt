package com.stephent.kotlin2capstoneproject

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CardAdapter (private var partNames: List<String>, private var partLink: List<String>) : RecyclerView.Adapter<CardAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val partTitle: TextView = itemView.findViewById(R.id.part_title)

        init{
            itemView.setOnClickListener{

                val position: Int = adapterPosition
                Toast.makeText(itemView.context, partNames[position], Toast.LENGTH_SHORT ).show()

                openWindow(partLink[position], itemView.context)


            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.computerpart_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return partNames.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.partTitle.text = partNames[position]

    }

    fun openWindow(urls: String, context: Context) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("window", true)
        intents.putExtras(b)
        context.startActivity(intents)
    }

}