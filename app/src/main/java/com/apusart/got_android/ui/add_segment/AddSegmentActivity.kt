package com.apusart.got_android.ui.add_segment

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.apusart.got_android.R
import com.apusart.got_android.api.models.handleResource
import com.apusart.got_android.databinding.SegmentFormBinding
import kotlinx.android.synthetic.main.segment_form.*

class AddSegmentActivity : AppCompatActivity() {

    private val viewModel: AddSegmentViewModel by viewModels()
    private lateinit var alertDialog: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: SegmentFormBinding =
            DataBindingUtil.setContentView(this, R.layout.segment_form)
        binding.viewModel = viewModel
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

        viewModel.segmentAdded.observe(this, { res ->
            handleResource(res,
                onSuccess = {
                    Toast.makeText(this, "Dodano odcinek", Toast.LENGTH_LONG).show()
                    finish()
                    add_segment_button_add.transitionToStart()
                }, onPending = {
                    add_segment_button_add.transitionToEnd()
                }, onError = { msg, _ ->
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
                    add_segment_button_add.transitionToStart()
                })
        })

        viewModel.points.observe(this, { res ->
            handleResource(res, {
                val arrayAdapterStartPoints = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    it?.map { p -> p.nazwa } ?: listOf())
                val arrayAdapterEndPoints = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    it?.map { p -> p.nazwa } ?: listOf())

                add_segment_start_point.adapter = arrayAdapterStartPoints

                add_segment_end_point.adapter = arrayAdapterEndPoints
            }, {

            }, { msg, _ ->
                Toast.makeText(this, "Nie udało się pobrać odcinków", Toast.LENGTH_SHORT).show()
            })
        })
    }

    private fun setupOnClickListeners() {
        add_segment_button_add.setOnClickListener {
            viewModel.addSegment(
                add_segment_start_point.getItemAtPosition(add_segment_start_point.selectedItemPosition) as String,
                add_segment_end_point.getItemAtPosition(add_segment_end_point.selectedItemPosition) as String
            )
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