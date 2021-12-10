package com.example.fulljson10

import Actor
import TitleData
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fulljson10.adapter.ActorsAdapter
import com.example.fulljson10.adapter.MyMovieAdapter
import com.example.fulljson10.common.Common
import com.example.fulljson10.model.FilmResponse
import com.example.fulljson10.retrofit.RetrofitServieces
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback



class ActorsViewPagerFragment() : Fragment() {

    lateinit var rvActors: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_actors_view_pager, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataAct = arguments?.getSerializable("t") as TitleData
        rvActors = view.findViewById(R.id.ActorRecicleView)
        rvActors.layoutManager = LinearLayoutManager(context)
        rvActors.setHasFixedSize(true)
        rvActors.adapter = ActorsAdapter(dataAct.actorList)



    }


}