package com.apusart.got_android.ui.manage_segment.segment_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.apusart.got_android.R
import com.apusart.got_android.api.models.handleResource
import com.apusart.got_android.databinding.SegmentListFragmentBinding
import kotlinx.android.synthetic.main.segment_list_fragment.*
import kotlinx.android.synthetic.main.segment_list_fragment.view.*

class SegmentListFragment: Fragment() {
    private val viewModel: SegmentListViewModel by viewModels()
    private lateinit var segmentAdapter: SegmentAdapter
    private lateinit var binding: SegmentListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.segment_list_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
        segmentAdapter = SegmentAdapter(findNavController())

        segment_list_fragment_list.apply {
            adapter = segmentAdapter
        }

        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSegments()
    }

    private fun setupButtons() {
        segment_list_fragment_header.setOnLeadingIconClickListener {
            findNavController().popBackStack()
        }

        segment_list_fragment_header.setOnTrailingIconClickListener {
            findNavController().navigate(R.id.addSegmentActivity)
        }
    }

    private fun setupObservers() {
        viewModel.segments.observe(viewLifecycleOwner, { res ->
            handleResource(res,
            onSuccess = {
                filterSegments(viewModel.searchText.value)
            })
        })

        viewModel.searchText.observe(viewLifecycleOwner, {
            filterSegments(it)
        })
    }

    private fun filterSegments(text: String?) {
        if (text == "" || text == null) {
            segmentAdapter.submitList(viewModel.segments.value?.data)
        } else {
            segmentAdapter.submitList(viewModel.segments.value?.data?.filter { segment -> segment.nazwa.contains(text)  })
        }
    }

}