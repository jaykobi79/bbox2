package com.sgtsoft.bbox2.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgtsoft.bbox2.databinding.FragmentGroupTitleListBinding

class GroupTitleListFragment : Fragment() {
    private lateinit var binding: FragmentGroupTitleListBinding
    private lateinit var viewModel: GroupTitleListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroupTitleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this).get(GroupTitleListViewModel::class.java)

        // Set up RecyclerView
        val groupTitleAdapter = GroupTitleAdapter { groupTitle ->
            val channelListFragment = ChannelListFragment()
            val args = Bundle()
            args.putString("group_title", groupTitle)
            channelListFragment.arguments = args

            parentFragmentManager.beginTransaction()
                .replace(R.id.container, channelListFragment)
                .addToBackStack(null)
                .commit()
        }
        binding.groupTitleRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupTitleAdapter
        }

        viewModel.groupTitles.observe(viewLifecycleOwner) { groupTitles ->
            groupTitleAdapter.submitList(groupTitles)
        }
    }
}
