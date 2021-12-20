package com.example.fulljson10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulljson10.adapter.ActorsAdapter
import com.example.fulljson10.viewmodel.ActorsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch



class ActorsViewPagerFragment: Fragment() {

    lateinit var rvActors: RecyclerView
    private val viewModelActors: ActorsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_actors_view_pager, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvActors = view.findViewById(R.id.ActorRecicleView)
        rvActors.layoutManager = LinearLayoutManager(context)
        rvActors.setHasFixedSize(true)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModelActors.resultActor.collect {
                if (it != null) {
                    rvActors.adapter = ActorsAdapter(it.actorList)
                }
            }
        }
    }


}