package com.mintu.assignment5paisa.models.watchlistone

import com.google.gson.annotations.SerializedName


data class WatchListOne (

    @SerializedName("Data"            ) var WatchListOneData            : ArrayList<WatchListOneData> = arrayListOf(),
    @SerializedName("MarketWatchName" ) var MarketWatchName : String?         = null,
    @SerializedName("Message"         ) var Message         : String?         = null,
    @SerializedName("Status"          ) var Status          : Int?            = null

)