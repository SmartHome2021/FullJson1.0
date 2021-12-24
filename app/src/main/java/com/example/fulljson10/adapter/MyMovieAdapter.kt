package com.example.fulljson10.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.fulljson10.R
import com.example.fulljson10.Top250Fragment
import com.example.fulljson10.interfaces.OnFilmSelectListener
import com.example.fulljson10.interfaces.OnLoadFilmListener
import com.example.fulljson10.model.Film
import com.example.fulljson10.room.FavoriteDao
import com.example.fulljson10.room.FavoriteEntity
import com.example.fulljson10.room.FilmDatabase
import com.example.fulljson10.viewmodel.Top250ViewModel
import kotlin.coroutines.coroutineContext


class MyMovieAdapter(
    private val context: Context,
    private var movieList: List<Film>,
    private val listener: OnFilmSelectListener
)
    : RecyclerView.Adapter<MyMovieAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View, private val listener: OnFilmSelectListener) :
        RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.filmPoster)
        val title: TextView = itemView.findViewById(R.id.filmTitle)
        val fullTitle: TextView = itemView.findViewById(R.id.filmFullTitle)
        val rank: TextView = itemView.findViewById(R.id.filmRank)
        val year: TextView = itemView.findViewById(R.id.filmYear)
        val rating: TextView = itemView.findViewById(R.id.filmRating)
        val count: TextView = itemView.findViewById(R.id.filmCount)
        val like: ImageButton = itemView.findViewById(R.id.filmLikeButton)


        fun bind(listItem: Film) {

            itemView.setOnClickListener {
                listener.onSelect(listItem)
            }
            like.setOnClickListener{
                listener.onLoad(listItem)
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







