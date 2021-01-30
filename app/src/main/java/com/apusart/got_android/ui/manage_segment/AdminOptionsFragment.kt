package com.apusart.got_android.ui.manage_segment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.apusart.got_android.R
import kotlinx.android.synthetic.main.admin_panel.*

class AdminPanel : Fragment(R.layout.admin_panel) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {
        admin_panel_header.setOnLeadingIconClickListener {
            requireActivity().finish()
        }
        admin_panel_manage_segments.setOnClickListener {
            findNavController().navigate(R.id.segmentListFragment)
        }
    }
}