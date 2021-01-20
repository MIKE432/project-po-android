package com.apusart.got_android.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.apusart.got_android.R
import com.apusart.got_android.ui.join_trip.JoinTripActivity
import com.apusart.got_android.ui.manage_segment.ManageSegmentActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpButtons()
    }

    private fun setUpButtons() {
        mateusz_pu09.setOnClickListener {
            Toast.makeText(this, "1", Toast.LENGTH_LONG).show()
        }

        mateusz_pu08.setOnClickListener {
            Toast.makeText(this, "2", Toast.LENGTH_LONG).show()
        }

        michal_pu10.setOnClickListener {
            startActivity(Intent(this, JoinTripActivity::class.java))
        }

        michal_pu16.setOnClickListener {
            startActivity(Intent(this, ManageSegmentActivity::class.java))
        }
    }
}