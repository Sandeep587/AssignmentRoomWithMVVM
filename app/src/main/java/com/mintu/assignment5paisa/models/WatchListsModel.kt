package com.mintu.assignment5paisa

import com.google.gson.annotations.SerializedName

data class WatchListsModel(

    @SerializedName("MWName") var MWName: ArrayList<MWName> = arrayListOf(),
    @SerializedName("Status") var Status: Int? = null

)