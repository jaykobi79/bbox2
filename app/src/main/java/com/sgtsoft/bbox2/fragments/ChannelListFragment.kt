package com.sgtsoft.bbox2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sgtsoft.bbox2.R
import com.sgtsoft.bbox2.adapter.ChannelListAdapter

class ChannelListFragment : Fragment() {

    private lateinit var channelListAdapter: ChannelListAdapter
    private val channelListViewModel: ChannelListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_channel_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupTitle = requireArguments().getString(ARG_GROUP_TITLE)

        channelListAdapter = ChannelListAdapter(emptyList())
        val recyclerView: RecyclerView = view.findViewById(R.id.channelListRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = channelListAdapter

        channelListViewModel.getTvgIdsByGroupTitle(groupTitle ?: "").observe(viewLifecycleOwner, { channels ->
            channels.map { it.tvgId }?.let { channelListAdapter.updateData(it as List<String>) }
        })
    }

    companion object {
        private const val ARG_GROUP_TITLE = "group_title"

        fun newInstance(groupTitle: String): ChannelListFragment {
            val fragment = ChannelListFragment()
            val args = Bundle()
            args.putString(ARG_GROUP_TITLE, groupTitle)
            fragment.arguments = args
            return fragment
        }
    }
}


