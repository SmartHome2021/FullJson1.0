package com.example.fulljson10


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fulljson10.viewmodel.DescriptionViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class DescriptionViewPagerFragment : Fragment() {

    lateinit var plotText: TextView
    private val viewModelDescription: DescriptionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        plotText = view.findViewById(R.id.DescriptionViewText)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModelDescription.resultDescription.collect{
                if (it != null) {
                    plotText.text = it.plot
                }
            }
        }

    }

}