package com.mintu.assignment5paisa.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mintu.assignment5paisa.models.watchlistone.WatchListOne
import com.mintu.assignment5paisa.models.watchlistone.WatchListOneData
import com.mintu.assignment5paisa.models.watchlistthree.WatchListThree
import com.mintu.assignment5paisa.models.watchlisttwo.WatchListTwo
import com.mintu.assignment5paisa.repository.WatchListRepository

class WatchListViewModel : ViewModel() {
    var jsonString: WatchListOne? = null
    var jsonStringTwo: WatchListTwo? = null
    var jsonStringThree: WatchListThree? = null

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Watchlist Data: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }

    fun getWatchListOneJsonData(context: Context): WatchListOne? {//,data: Data
        jsonString = WatchListRepository.getWatchListOneJsonData(context)
        return jsonString;
    }

    fun getWatchListSecondJsonData(context: Context): WatchListTwo? {//,data: Data
        jsonStringTwo = WatchListRepository.getWatchListSecondJsonData(context)
        return jsonStringTwo;
    }

    fun getWatchListThreeJsonData(context: Context): WatchListThree? {//,data: Data
        jsonStringThree = WatchListRepository.getWatchListThreeJsonData(context)
        return jsonStringThree;
    }


    fun insertWatchOneData(context: Context, watchListOneData: WatchListOneData) {
        WatchListRepository.insertWatchOneData(context, watchListOneData)
    }

    fun insertWatchTwoData(
        context: Context,
        watchListTwoData: com.mintu.assignment5paisa.models.watchlisttwo.WatchListTwoData
    ) {
        WatchListRepository.insertWatchTwoData(context, watchListTwoData)

    }

    fun insertWatchThreeData(
        context: Context,
        watchListThreeData: com.mintu.assignment5paisa.models.watchlistthree.WatchListThreeData
    ) {
        WatchListRepository.insertWatchThreeData(context, watchListThreeData)
    }

    fun getWatchListOneData(context: Context): List<WatchListOneData> {
        return WatchListRepository.getWatchListOneData(context)
    }

    fun getWatchListTwoData(context: Context): List<com.mintu.assignment5paisa.models.watchlisttwo.WatchListTwoData> {
        return WatchListRepository.getWatchListTwoData(context)
    }

    fun getWatchListThreeData(context: Context): List<com.mintu.assignment5paisa.models.watchlistthree.WatchListThreeData> {
        return WatchListRepository.getWatchListThreeData(context)
    }

    fun updateLastTradePriceWatchListThree(context: Context, price: Double, id: Int) {
        return WatchListRepository.updateLastTradePriceWatchListThree(context, price, id)
    }

    fun updateLastTradePriceWatchListTwo(context: Context, price: Double, id: Int) {
        return WatchListRepository.updateLastTradePriceWatchListTwo(context, price, id)
    }

    fun updateLastTradePriceWatchListOne(context: Context, price: Double, id: Int) {
        return WatchListRepository.updateLastTradePriceWatchListOne(context, price, id)
    }

    fun updateLastOperatorWatchListThree(context: Context, operator: String, id: Int) {
        return WatchListRepository.updateLastOperatorWatchListThree(context, operator, id)
    }

    fun updateLastOperatorWatchListTwo(context: Context, operator: String, id: Int) {
        return WatchListRepository.updateLastOperatorWatchListTwo(context, operator, id)
    }

    fun updateLastOperatorWatchListOne(context: Context, operator: String, id: Int) {
        return WatchListRepository.updateLastOperatorWatchListOne(context, operator, id)
    }

}