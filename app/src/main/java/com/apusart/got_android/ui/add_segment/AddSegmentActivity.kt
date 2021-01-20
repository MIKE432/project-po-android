package com.apusart.got_android.ui.add_segment

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.apusart.got_android.R
import com.apusart.got_android.databinding.SegmentFormBinding
import kotlinx.android.synthetic.main.segment_form.*

class AddSegmentActivity: AppCompatActivity() {

    private val viewModel: AddSegmentViewModel by viewModels()
    private lateinit var alertDialog: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: SegmentFormBinding = DataBindingUtil.setContentView(this, R.layout.segment_form)
        binding.viewModel= viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        setupAlertDialog()
        setupObservers()
        setupOnClickListeners()
    }

    private fun setupObservers() {
        add_segment_button_add.isEnabled = viewModel.isFormValid()

        viewModel.segmentNameText.observe(this, {
            add_segment_button_add.isDisabled = !viewModel.isFormValid()
        })

        viewModel.segmentStartIdText.observe(this, {
            add_segment_button_add.isDisabled = !viewModel.isFormValid()
        })

        viewModel.segmentEndIdText.observe(this, {
            add_segment_button_add.isDisabled = !viewModel.isFormValid()
        })
    }

    private fun setupOnClickListeners() {
        add_segment_button_add.setOnClickListener {
            add_segment_button_add.transitionToEnd()
        }

        add_segment_form_header.setOnLeadingIconClickListener {
            if (viewModel.isFormDirty())
                alertDialog.show()
            else
                finish()
        }
    }

    private fun setupAlertDialog() {
        alertDialog = AlertDialog.Builder(this)
        alertDialog
            .setTitle(getString(R.string.throw_changes))
            .setMessage(getString(R.string.throw_changes_expl))
            .setPositiveButton(R.string.yes) { _, _ ->
                finish()
            }
            .setNegativeButton(R.string.abort) { dialog, _ ->
                dialog.cancel()
            }
    }
}