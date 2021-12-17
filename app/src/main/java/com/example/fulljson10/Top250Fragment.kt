package com.example.fulljson10

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulljson10.adapter.MyMovieAdapter
import com.example.fulljson10.common.Common
import com.example.fulljson10.model.Film
import androidx.navigation.fragment.findNavController
import com.example.fulljson10.adapter.OnFilmSelectListener
import com.example.fulljson10.databinding.Top250RecicleBinding
import com.example.fulljson10.model.Top250ViewModel
import com.example.fulljson10.retrofit.RetrofitServieces
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*


class Top250Fragment : Fragment(), OnFilmSelectListener {
    lateinit var itemCount: TextView

    lateinit var timeCount: TextView

    private val viewModel250View: Top250ViewModel by viewModels()

    private var fragmentFirstBinding: Top250RecicleBinding? = null
    private val binding get() = fragmentFirstBinding!!

    lateinit var mService: RetrofitServieces

    lateinit var progressBar: ProgressBar

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

        timeCount = view.findViewById(R.id.TimeCount)

        progressBar = view.findViewById(R.id.progressBar)

        rvFilms = view.findViewById(R.id.list)

        mService = Common.retrofitService

        rvFilms.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(context)

        rvFilms.layoutManager = layoutManager

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel250View.result250.collect {
                if (it != null) {
                progressBar.visibility = View.VISIBLE
                adapter  = MyMovieAdapter(requireContext(),it, this@Top250Fragment )
                rvFilms.adapter = adapter
                progressBar.visibility = View.INVISIBLE
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch{
            viewModel250View.itemCount.collect {
                if (it != null)
                {
                    itemCount.text = "Total Films: $it"
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch{
            viewModel250View.timeCount.collect {
                if (it != null) {
                    timeCount.text = "The time has passed: $it ms"
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


