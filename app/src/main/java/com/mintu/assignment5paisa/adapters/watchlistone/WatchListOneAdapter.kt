package com.mintu.assignment5paisa.adapters.watchlistone

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mintu.assignment5paisa.R
import com.mintu.assignment5paisa.adapters.watchlistone.WatchListOneAdapter.ViewHolder
import com.mintu.assignment5paisa.models.watchlistone.WatchListOneData
import com.mintu.assignment5paisa.utils.Helpers
import kotlin.math.roundToInt

class WatchListOneAdapter(
    private val itemList: List<WatchListOneData>,
    private val isGrid: String,
    val listener: (WatchListOneData) -> Unit
) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layout =
            if (isGrid == "true") R.layout.watchlist_item_grid else R.layout.watchlist_item_vertical
        val view = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (itemList[position].Operator) {
            "+" -> {
                holder.lastTradePriceTextView.setTextColor(Color.GREEN)
            }
            "-" -> {
                holder.lastTradePriceTextView.setTextColor(Color.RED)
            }
            else -> {
                holder.lastTradePriceTextView.setTextColor(Color.GRAY)
            }
        }
        holder.shortNameTextView.text = itemList[position].ShortName
        holder.lastTradePriceTextView.text =
            itemList[position].LastTradePrice.roundToInt().toString()
        holder.exchTextView.text = "${itemList[position].Exch} ${itemList[position].ExchType}"
        holder.pCloseTextView.text = itemList[position].PClose.toString()
        holder.volTextView.text =
            "Vol ${itemList[position].Volume.let { Helpers.formatVolume(it) }}"
        holder.lasTradeTimeTextView.text =
            itemList[position].LastTradeTime.let { Helpers.formatTime(it) }
        holder.itemView.setOnClickListener {
            listener(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var shortNameTextView: TextView
        var lastTradePriceTextView: TextView
        var exchTextView: TextView
        var pCloseTextView: TextView
        var volTextView: TextView
        var lasTradeTimeTextView: TextView

        init {
            shortNameTextView = itemView.findViewById(R.id.shortNameTextView) as TextView
            lastTradePriceTextView = itemView.findViewById(R.id.lastTradePriceTextView) as TextView
            exchTextView = itemView.findViewById(R.id.exchTextView) as TextView
            pCloseTextView = itemView.findViewById(R.id.pCloseTextView) as TextView
            volTextView = itemView.findViewById(R.id.volTextView) as TextView
            lasTradeTimeTextView = itemView.findViewById(R.id.lastTradeTimeTextView) as TextView
        }
    }
}