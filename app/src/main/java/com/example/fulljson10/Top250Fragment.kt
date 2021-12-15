package com.example.fulljson10

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulljson10.adapter.MyMovieAdapter
import com.example.fulljson10.common.Common
import com.example.fulljson10.model.Film
import com.example.fulljson10.model.FilmResponse
import androidx.navigation.fragment.findNavController
import com.example.fulljson10.adapter.OnFilmSelectListener
import com.example.fulljson10.databinding.Top250RecicleBinding
import com.example.fulljson10.model.Top250Model
import com.example.fulljson10.retrofit.RetrofitServieces
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.util.*


class Top250Fragment : Fragment(), OnFilmSelectListener {
    lateinit var itemCount: TextView

    private val viewModel250: Top250Model by viewModels()

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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemCount = view.findViewById(R.id.FilmCount250)


        rvFilms = view.findViewById(R.id.list)

        mService = Common.retrofitService

        rvFilms.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(context)

        rvFilms.layoutManager = layoutManager

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel250.result250.collect {
                if (it != null) {
                adapter  = MyMovieAdapter(requireContext(),it, this@Top250Fragment )
                rvFilms.adapter = adapter
//                    delay(1000)
//                    itemCount.setText("Items downloaded: ${it.size.toString()}")
//                    it.size
                    itemCount.setText(viewModel250.conterItem.toString())
                }
            }
        }



    }


    override fun onSelect(film: Film) {

        val bundle = Bundle()

        bundle.putString ("f", film.id)
        findNavController().navigate(R.id.action_Top250Fragment_to_filmFullTitle, bundle)
//        Toast.makeText(requireContext(), film.fullTitle, Toast.LENGTH_SHORT).show()

    }
}

