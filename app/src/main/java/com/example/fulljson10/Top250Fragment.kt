package com.example.fulljson10

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulljson10.adapter.Top250Adapter
import com.example.fulljson10.common.Common
import com.example.fulljson10.model.Film
import androidx.navigation.fragment.findNavController
import com.example.fulljson10.interfaces.OnFilmSelectListener
import com.example.fulljson10.databinding.Top250RecicleBinding
import com.example.fulljson10.viewmodel.Top250ViewModel
import com.example.fulljson10.retrofit.RetrofitServieces
//import com.example.fulljson10.room.FavoriteDatabase
import com.example.fulljson10.room.FavoriteEntity
import com.example.fulljson10.room.FilmDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*


class Top250Fragment : Fragment(), OnFilmSelectListener {

    private lateinit var mDb: FilmDatabase

    lateinit var itemCount: TextView

    lateinit var timeCount: TextView

    private val viewModel250View: Top250ViewModel by viewModels()

    private var fragmentFirstBinding: Top250RecicleBinding? = null

    private val binding get() = fragmentFirstBinding!!

    lateinit var mService: RetrofitServieces

    lateinit var progressBar: ProgressBar

    lateinit var searchBar: EditText

    lateinit var clearButton: ImageButton

    lateinit var layoutManager: LinearLayoutManager

    lateinit var adapter: Top250Adapter

    lateinit var rvFilms: RecyclerView

    lateinit var favorite: FloatingActionButton

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
        mDb = context?.let { FilmDatabase.getInstance(it)} !!

        itemCount = view.findViewById(R.id.FilmCount250)

        timeCount = view.findViewById(R.id.TimeCount)

        progressBar = view.findViewById(R.id.progressBar)

        searchBar = view.findViewById(R.id.SearchBar250)

        clearButton = view.findViewById(R.id.ClearButton)

        rvFilms = view.findViewById(R.id.list)

        mService = Common.retrofitService

        rvFilms.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(context)

        rvFilms.layoutManager = layoutManager

        searchBar.addTextChangedListener(textWatcher)

        favorite = view.findViewById(R.id.FloatingButton)

        favorite.setOnClickListener{
            findNavController().navigate(R.id.action_Top250Fragment_to_favoriteFragment)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel250View.result250.collect {
                if (it != null) {
                progressBar.visibility = View.VISIBLE
                adapter  = Top250Adapter(requireContext(),it, this@Top250Fragment)
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

        clearButton.setOnClickListener{
            searchBar.setText("")
        }


    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            updateSearch()
        }

        override fun afterTextChanged(s: Editable?) {}

    }

    private fun updateSearch(){
        val s = searchBar.text.toString()

        if (s.isEmpty()) {
            viewModel250View.clear()
            clearButton.visibility = View.GONE
        } else {
            viewModel250View.search(s)
            clearButton.visibility = View.VISIBLE
        }
    }


    override fun onSelect(film: Film) {
        val bundle = Bundle()
        bundle.putString ("s", film.id)
        findNavController().navigate(R.id.action_Top250Fragment_to_filmFullTitle, bundle)

    }

    override fun onLoad(film: Film) {
        viewLifecycleOwner.lifecycleScope.launch{
            withContext(Dispatchers.IO){
                mDb.filmDao().insert(FavoriteEntity(film.id))
                Log.i("DataInsert", "запись фильма ${film.title} с ID ${film.id} добавлена")
            }
        }
    }

    override fun onDelete(film: Film) {
        viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.IO){
                mDb.filmDao().delete(FavoriteEntity(film.id))
                Log.i("DataInsert", "запись фильма ${film.title} с ID ${film.id} удалена")
            }
        }
    }

    override fun onFavorite(film: Film) {
        viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val xcv: List<FavoriteEntity> = mDb.filmDao().readAll()
                viewModel250View.isFavorite(xcv)
                Log.i("DataInsert", "${xcv.size}")

            }
        }
    }


}


