package com.apusart.got_android.ui.segment_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.apusart.got_android.R
import com.apusart.got_android.api.models.handleResource
import kotlinx.android.synthetic.main.segment_details.*

class SegmentDetails : Fragment(R.layout.segment_details) {
    private val viewModel: SegmentDetailsViewModel by viewModels()

    private val navArgs by navArgs<SegmentDetailsArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.getSegment(navArgs.segmentId)
        setupOnClickListeners()
    }

    private fun setupObservers() {
        viewModel.segment.observe(viewLifecycleOwner, { res ->
            handleResource(res,
                onSuccess = {
                    segment_details_region.text = it?.poczatek?.grupa?.nazwa ?: "Brak"
                    segment_details_name.text = it?.nazwa ?: "Brak"
                    segment_details_id.text = it?.id.toString()
                    segment_details_length.text = it?.dlugosc.toString() + "km"
                    segment_details_start_point_id.text = it?.poczatek?.id.toString()
                    segment_details_end_point_id.text = it?.koniec?.id.toString()

                    segment_details_open_segment_btn.title =
                        if (it?.czyAktywny == true)
                            "Zamknij odcinek"
                        else
                            "Otwórz odcinek"

                    segment_details_open_segment_btn.transitionToStart()
                }, onPending = {
                    segment_details_open_segment_btn.transitionToEnd()
                }, onError = { msg, _ ->
                    segment_details_open_segment_btn.transitionToStart()
                })
        })
    }

    private fun setupOnClickListeners() {
        segment_details_open_segment_btn.setOnClickListener {
            viewModel.toggleSegment()
        }

        segment_details_header.setOnLeadingIconClickListener {
            findNavController().popBackStack()
        }
    }
}