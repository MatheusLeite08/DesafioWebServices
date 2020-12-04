package com.example.desafiowebservices.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiowebservices.R
import com.example.desafiowebservices.entities.Results
import com.squareup.picasso.Picasso

class ComicsListAdapter(val listener: onComicClickListener) :
    RecyclerView.Adapter<ComicsListAdapter.ComicsListViewHolder>() {
    var comicsList = ArrayList<Results>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsListViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_quadrinho, parent, false)
        return ComicsListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ComicsListViewHolder, position: Int) {
        val currentItem: Results = comicsList[position]

        //Como algumas HQ's tem issueNumber do tipo Double e outras do tipo Int. Para manter o padrão do
        //protótipo é feita a seguinte análise
        if ((currentItem.issueNumber - (currentItem.issueNumber).toInt()) != 0.0) {
            holder.comicNumber.text = ("#" + currentItem.issueNumber.toString())
        }else{
            holder.comicNumber.text = ("#" + ((currentItem.issueNumber).toInt()).toString())
        }


        Picasso.get().load(currentItem.thumbnail.path + "." + currentItem.thumbnail.extension)
            .into(holder.comicCover)

    }

    override fun getItemCount(): Int {
        return comicsList.size
    }

    interface onComicClickListener {
        fun comicClick(position: Int)
    }

    inner class ComicsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val comicCover: ImageView = itemView.findViewById(R.id.iv_comicCover)
        val comicNumber: TextView = itemView.findViewById(R.id.tv_comicNumber)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.comicClick(position)
        }

    }

    fun addList(list: ArrayList<Results>) {
        comicsList.addAll(list)
        notifyDataSetChanged()
    }

}