package com.mintu.assignment5paisa.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.mintu.assignment5paisa.R
import com.mintu.assignment5paisa.models.watchlistone.WatchListOneData

class BottomSheetFragment(private val item: String) : BottomSheetDialogFragment() {

    private lateinit var dayHighTextView: TextView
    private lateinit var dayLowTextView: TextView
    private lateinit var nseBseCodeTextView: TextView
    private lateinit var scripCodeTextView: TextView
    private lateinit var model: WatchListOneData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.other_watchlist_data_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        convertJsonToDataClass(item)
        populateData()
    }

    private fun convertJsonToDataClass(item: String) {
        model = Gson().fromJson(item, WatchListOneData::class.java)
    }

    private fun populateData() {
        dayHighTextView.text = model.DayHigh
        dayLowTextView.text = model.DayLow
        nseBseCodeTextView.text = model.NseBseCode
        scripCodeTextView.text = model.ScripCode
    }

    fun initView(view: View) {
        dayHighTextView = view.findViewById(R.id.dayHighTextView)
        dayLowTextView = view.findViewById(R.id.dayLowTextView)
        nseBseCodeTextView = view.findViewById(R.id.nseBseCodeTextView)
        scripCodeTextView = view.findViewById(R.id.scripCodeTextView)
    }
}