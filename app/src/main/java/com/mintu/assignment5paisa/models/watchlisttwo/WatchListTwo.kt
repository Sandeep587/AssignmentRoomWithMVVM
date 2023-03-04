package com.mintu.assignment5paisa.models.watchlisttwo

import com.google.gson.annotations.SerializedName


data class WatchListTwo (

    @SerializedName("Data"            ) var WatchListTwoData            : ArrayList<WatchListTwoData> = arrayListOf(),
    @SerializedName("MarketWatchName" ) var MarketWatchName : String?         = null,
    @SerializedName("Message"         ) var Message         : String?         = null,
    @SerializedName("Status"          ) var Status          : Int?            = null

)