package com.mintu.assignment5paisa.models.watchlistthree

import com.google.gson.annotations.SerializedName


data class WatchListThree (

    @SerializedName("Data"            ) var WatchListThreeData            : ArrayList<WatchListThreeData> = arrayListOf(),
    @SerializedName("MarketWatchName" ) var MarketWatchName : String?         = null,
    @SerializedName("Message"         ) var Message         : String?         = null,
    @SerializedName("Status"          ) var Status          : Int?            = null

)