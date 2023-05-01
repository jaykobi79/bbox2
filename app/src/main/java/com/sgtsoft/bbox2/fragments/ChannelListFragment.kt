package com.sgtsoft.bbox2.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgtsoft.bbox2.R

class ChannelListFragment : Fragment() {

    companion object {
        fun newInstance() = ChannelListFragment()
    }

    private lateinit var viewModel: ChannelListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_channel_list, container, false)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChannelListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}