package com.example.fulljson10

import TitleData
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.fulljson10.adapter.MyTitleAdapter
import com.example.fulljson10.adapter.ViewPagerAdapter
import com.example.fulljson10.common.Common
import com.example.fulljson10.retrofit.RetrofitServieces
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FullTitleFragment : Fragment() {


    lateinit var mService: RetrofitServieces
    lateinit var adapter: MyTitleAdapter
    lateinit var rvFilms: RecyclerView

    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
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
    //    lateinit var tvDescription: TextView
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
//        tvDescription = view.findViewById(R.id.TitleDescription)
        tvDirector = view.findViewById(R.id.TitleDirector)
        tvStars = view.findViewById(R.id.TitleStars)

        viewLifecycleOwner.lifecycleScope.launch {

        }


    }



    suspend fun getAllMovieList() {
        val titleId = arguments?.getString("f") as String

        kotlin.runCatching { withContext(Dispatchers.Main){mService.getTitleList(titleId)} }
            .onSuccess { response ->

                pagerAdapter = ViewPagerAdapter(this, response)
                viewPager.adapter = pagerAdapter
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = tabNames[position]
                }.attach()

                ui(response)
                adapter = MyTitleAdapter(requireContext(), response.images.items)
                rvFilms.adapter = adapter

            }
            .onFailure { e ->
                Log.e("Response", e.message, e)
            }


//        val titleId = arguments?.getString("f") as String
//        mService.getTitleList(titleId).enqueue(object : Callback<TitleData> {
//            override fun onFailure(call: Call<TitleData>, t: Throwable) {
//            }
//
//
//            override fun onResponse(call: Call<TitleData>, response: Response<TitleData>) {
//                Log.e("Response", "FullTitle")
//                val titlesResponse = response.body()
//                if (titlesResponse != null) {
//                    actData = titlesResponse
//                }
//                if (titlesResponse == null) {
//                    return
//                }
//                pagerAdapter = ViewPagerAdapter(this@FullTitleFragment, actData)
//                viewPager.adapter = pagerAdapter
//                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//                    tab.text = tabNames[position]
//                }.attach()
//
//                ui(titlesResponse)
//
//                adapter = MyTitleAdapter(context!!, titlesResponse.images.items)
//                adapter.notifyDataSetChanged()
//                rvFilms.adapter = adapter
//
//            }
//
//        })
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








