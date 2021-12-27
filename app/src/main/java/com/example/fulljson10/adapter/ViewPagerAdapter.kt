package com.example.fulljson10.adapter


import TitleData
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fulljson10.ActorsViewPagerFragment
import com.example.fulljson10.DescriptionViewPagerFragment

class ViewPagerAdapter(fragment: Fragment, private val response: TitleData): FragmentStateAdapter(fragment){

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {


        when(position) {
            0 -> return ActorsViewPagerFragment().apply {
                arguments = Bundle()
                arguments?.putSerializable("t", response)
            }
            1 -> return DescriptionViewPagerFragment().apply {
                arguments = Bundle()
                arguments?.putSerializable("t", response)

            }
            else -> return ActorsViewPagerFragment()
        }
    }

}


