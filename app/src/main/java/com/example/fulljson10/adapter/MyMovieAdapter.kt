package com.example.fulljson10.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fulljson10.R
import com.example.fulljson10.model.Film


class MyMovieAdapter(
    private val context: Context,
    private val movieList: List<Film>,
    private val listener: OnFilmSelectListener
)
    : RecyclerView.Adapter<MyMovieAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View, val listener: OnFilmSelectListener) :
        RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.filmPoster)
        val title: TextView = itemView.findViewById(R.id.filmTitle)
        val fullTitle: TextView = itemView.findViewById(R.id.filmFullTitle)
        val rank: TextView = itemView.findViewById(R.id.filmRank)
        val year: TextView = itemView.findViewById(R.id.filmYear)
        val rating: TextView = itemView.findViewById(R.id.filmRating)
        val count: TextView = itemView.findViewById(R.id.filmCount)

        fun bind(listItem: Film) {
            itemView.setOnClickListener {
                listener.onSelect(listItem)
            }
            Glide.with(poster.context).load(listItem.image).into(poster)
            title.text = listItem.title
            fullTitle.text = listItem.fullTitle
            rank.text = listItem.rank.toString()
            year.text = (year.context.getString(R.string.Year) + "  " + listItem.year.toString())
            rating.text =
                (rating.context.getString(R.string.Rating) + "  " + listItem.imDbRating.toString())
            count.text =
                (count.context.getString(R.string.Count) + "  " + listItem.imDbRatingCount.toString())



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        return MyViewHolder(itemView, listener)
    }

    override fun getItemCount() = movieList.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = movieList[position]
        holder.bind(listItem)
    }
}