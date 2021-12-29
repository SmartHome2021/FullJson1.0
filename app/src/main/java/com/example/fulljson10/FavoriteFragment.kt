package com.example.fulljson10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulljson10.adapter.FavoriteAdapter
import com.example.fulljson10.adapter.Top250Adapter
import com.example.fulljson10.databinding.FavoriteRecicleBinding
import com.example.fulljson10.databinding.Top250RecicleBinding
import com.example.fulljson10.interfaces.OnFilmSelectListener
import com.example.fulljson10.model.Film
import com.example.fulljson10.viewmodel.FavoriteViewModel
import com.example.fulljson10.viewmodel.Top250ViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteFragment: Fragment(), OnFilmSelectListener {

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private var fragmentFavoriteBinding: FavoriteRecicleBinding? = null
    private val binding get() = fragmentFavoriteBinding!!

    lateinit var adapter: FavoriteAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var rvFavorite: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentFavoriteBinding = FavoriteRecicleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvFavorite = view.findViewById(R.id.FavoriteRecicle)
        rvFavorite.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        rvFavorite.layoutManager = layoutManager

        viewLifecycleOwner.lifecycleScope.launch {
            favoriteViewModel.favorite250.collect{
                if (it != null){
                    adapter = FavoriteAdapter(it, this@FavoriteFragment)
                    rvFavorite.adapter = adapter
                }
            }

        }

    }

    override fun onSelect(film: Film) {
        TODO("Not yet implemented")
    }

    override fun onLoad(film: Film) {
        TODO("Not yet implemented")
    }

    override fun onDelete(film: Film) {
        TODO("Not yet implemented")
    }

    override fun onFavorite(film: List<Film>) {
        TODO("Not yet implemented")
    }



}