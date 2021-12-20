package com.example.fulljson10

import TitleData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.fulljson10.adapter.MyTitleAdapter
import com.example.fulljson10.adapter.ViewPagerAdapter
import com.example.fulljson10.common.Common
import com.example.fulljson10.viewmodel.FullTitleViewModel
import com.example.fulljson10.retrofit.RetrofitServieces
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class FullTitleFragment : Fragment() {

    lateinit var mService: RetrofitServieces
    lateinit var adapter: MyTitleAdapter
    lateinit var rvFilms: RecyclerView

    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val viewModelFullTitle: FullTitleViewModel by viewModels()
    private val tabNames: Array<String> = arrayOf(
        "Actors",
        "Description"
    )


    lateinit var tvTitle: TextView
    lateinit var tvPoster: ImageView
    lateinit var tvRating: TextView
    lateinit var tvPopular: TextView
    lateinit var tvGenre: TextView
    lateinit var tvYear: TextView
    lateinit var tvDirector: TextView
    lateinit var tvStars: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_full_title, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.TitleViewPager)
        tabLayout = view.findViewById(R.id.TitleTabLayout)
        rvFilms = view.findViewById(R.id.TitleRecicle)
        mService = Common.retrofitService
        rvFilms.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvFilms.layoutManager = layoutManager


        tvTitle = view.findViewById(R.id.TitleTitle)
        tvPoster = view.findViewById(R.id.TitlePoster)
        tvRating = view.findViewById(R.id.TitleRating)
        tvPopular = view.findViewById(R.id.TitlePopular)
        tvGenre = view.findViewById(R.id.TitleGenre)
        tvYear = view.findViewById(R.id.TitleYear)
        tvDirector = view.findViewById(R.id.TitleDirector)
        tvStars = view.findViewById(R.id.TitleStars)



//        val titleId = arguments?.getString("f") as String


        viewLifecycleOwner.lifecycleScope.launch {
            viewModelFullTitle.resultFullTitle.collect {
                if (it != null) {
                    pagerAdapter = ViewPagerAdapter(this@FullTitleFragment, it)
                    viewPager.adapter = pagerAdapter
                    TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                        tab.text = tabNames[position]
                    }.attach()

                    ui(it)

                    adapter = MyTitleAdapter(requireContext(), it.images.items)
                    rvFilms.adapter = adapter


                }
            }
        }
//        viewModelFullTitle.load(titleId)
    }



    private fun ui(titles: TitleData)
    {

        Glide.with(tvPoster.context).load(titles.image).into(tvPoster)
        tvTitle.text = titles.title
        tvRating.text = ("ImDb rating : ${titles.imDbRating}")
        tvPopular.text = ("Metascore: ${titles.metacriticRating}")
        tvGenre.text = ("Genre: ${titles.genres}")
        tvYear.text = ("Year: ${titles.year}")
//        tvDescription.text = ("Description:" + "\n" + titles.plot)
        tvDirector.text = ("Director: " + titles.directors)
        tvStars.text = ("Stars: " + titles.stars)


    }

}












