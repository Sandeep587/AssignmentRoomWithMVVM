package com.mintu.assignment5paisa.models.watchlistone

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "WatchListOne")
data class WatchListOneData(

    @SerializedName("DayHigh")
    @ColumnInfo(name = "DayHigh")
    var DayHigh: String,
    @SerializedName("DayLow")
    @ColumnInfo(name = "DayLow")
    var DayLow: String,
    @SerializedName("EPS")
    @ColumnInfo(name = "EPS")
    var EPS: String,
    @SerializedName("Exch")
    @ColumnInfo(name = "Exch")
    var Exch: String,
    @SerializedName("ExchType")
    @ColumnInfo(name = "ExchType")
    var ExchType: String,
    @SerializedName("FullName")
    @ColumnInfo(name = "FullName")
    var FullName: String,
    @SerializedName("High52Week")
    @ColumnInfo(name = "High52Week")
    var High52Week: String,
    @SerializedName("LastTradePrice")
    @ColumnInfo(name = "LastTradePrice")
    var LastTradePrice: Double,
    @SerializedName("LastTradeTime")
    @ColumnInfo(name = "LastTradeTime")
    var LastTradeTime: String,
    @SerializedName("Low52Week")
    @ColumnInfo(name = "Low52Week")
    var Low52Week: String,
    @SerializedName("Month")
    @ColumnInfo(name = "Month")
    var Month: String,
    @SerializedName("Name")
    @ColumnInfo(name = "Name")
    var Name: String,
    @SerializedName("NseBseCode")
    @ColumnInfo(name = "NseBseCode")
    var NseBseCode: String,
    @SerializedName("PClose")
    @ColumnInfo(name = "PClose")
    var PClose: Double,
    @SerializedName("Quarter")
    @ColumnInfo(name = "Quarter")
    var Quarter: String,
    @SerializedName("ScripCode")
    @ColumnInfo(name = "ScripCode")
    var ScripCode: String,
    @SerializedName("ShortName")
    @ColumnInfo(name = "ShortName")
    var ShortName: String,
    @SerializedName("Volume")
    @ColumnInfo(name = "Volume")
    var Volume: Int,
    @SerializedName("Year")
    @ColumnInfo(name = "Year")
    var Year: String,
    @ColumnInfo(name = "isLoggedIn")
    var isLoggedIn: Boolean,
    @SerializedName("Operator")
    @ColumnInfo(name = "Operator")
    var Operator: String,
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}