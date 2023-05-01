package com.sgtsoft.bbox2.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sgtsoft.bbox2.R
import com.sgtsoft.bbox2.adapter.GroupTitleListAdapter
import com.sgtsoft.bbox2.data.database.M3uItemDatabase
import com.sgtsoft.bbox2.data.repository.M3uItemRepository
import com.sgtsoft.bbox2.databinding.FragmentGroupTitleListBinding



    class GroupTitleListFragment : Fragment() {

        private lateinit var binding: FragmentGroupTitleListBinding
        private lateinit var viewModel: GroupTitleListViewModel

        @SuppressLint("NotifyDataSetChanged")
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentGroupTitleListBinding.inflate(inflater, container, false)

            val adapter = GroupTitleListAdapter(emptyList()) { groupTitle ->
                val channelListFragment = ChannelListFragment().apply {
                    arguments = Bundle().apply {
                        putString("selectedGroupTitle", groupTitle)
                    }
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, channelListFragment)
                    .addToBackStack(null)
                    .commit()
            }

            // Set up the layout manager and the adapter for the RecyclerView
            binding.groupTitleRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.groupTitleRecyclerView.adapter = adapter

            viewModel = ViewModelProvider(this, GroupTitleListViewModelFactory(
                M3uItemRepository(M3uItemDatabase.getDatabase(requireContext()).m3uItemDao())
            )).get(GroupTitleListViewModel::class.java)

            viewModel.groupTitles.observe(viewLifecycleOwner) { groupTitles ->
                adapter.groupTitles = groupTitles
                adapter.notifyDataSetChanged()
            }

            return binding.root
        }
    }








