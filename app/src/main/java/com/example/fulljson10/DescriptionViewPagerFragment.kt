package com.example.fulljson10

import TitleData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class DescriptionViewPagerFragment : Fragment() {

    lateinit var plotText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plotAct = arguments?.getSerializable("t") as TitleData
        plotText = view.findViewById(R.id.DescriptionViewText)
        plotText.text = plotAct.plot

    }

}