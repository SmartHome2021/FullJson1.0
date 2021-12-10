package com.example.fulljson10

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulljson10.R
import com.example.fulljson10.adapter.MyMovieAdapter
import com.example.fulljson10.common.Common
import com.example.fulljson10.model.Film
import com.example.fulljson10.model.FilmResponse
import androidx.navigation.fragment.findNavController
import com.example.fulljson10.adapter.OnFilmSelectListener
import com.example.fulljson10.databinding.Top250RecicleBinding
import com.example.fulljson10.retrofit.RetrofitServieces
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.util.*


class Top250Fragment : Fragment(), OnFilmSelectListener {

    private var fragmentFirstBinding: Top250RecicleBinding? = null
    private val binding get() = fragmentFirstBinding!!

    lateinit var mService: RetrofitServieces

    lateinit var layoutManager: LinearLayoutManager

    lateinit var adapter: MyMovieAdapter

    lateinit var rvFilms: RecyclerView

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentFirstBinding = Top250RecicleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rvFilms = view.findViewById(R.id.list)

        mService = Common.retrofitService

        rvFilms.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(context)

        rvFilms.layoutManager = layoutManager

        getAllMovieList()


    }


    private fun getAllMovieList() {

        mService.getMovieList().enqueue(object : Callback<FilmResponse> {

            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
    Log.e("Response", "Top250")

                val filmsResponse = response.body()

                adapter = MyMovieAdapter(context!!, filmsResponse!!.items, this@Top250Fragment)

                adapter.notifyDataSetChanged()
                rvFilms.adapter = adapter
            }
        })
    }

    override fun onSelect(film: Film) {

        val bundle = Bundle()

        bundle.putString ("f", film.id)
        findNavController().navigate(R.id.action_Top250Fragment_to_filmFullTitle, bundle)
//        Toast.makeText(requireContext(), film.fullTitle, Toast.LENGTH_SHORT).show()

    }
}

