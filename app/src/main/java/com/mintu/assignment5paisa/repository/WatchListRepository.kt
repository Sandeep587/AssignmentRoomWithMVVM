package com.mintu.assignment5paisa.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.mintu.assignment5paisa.R
import com.mintu.assignment5paisa.models.watchlistone.WatchListOne
import com.mintu.assignment5paisa.models.watchlistone.WatchListOneData
import com.mintu.assignment5paisa.models.watchlistthree.WatchListThree
import com.mintu.assignment5paisa.models.watchlisttwo.WatchListTwo
import com.mintu.assignment5paisa.room.LoginDatabase
import com.mintu.assignment5paisa.utils.Helpers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WatchListRepository {
    companion object {
        var loginDatabase: LoginDatabase? = null
        var watchListOne: LiveData<WatchListOneData>? = null
        val gson: Gson = Gson()

        private fun initializeDB(context: Context): LoginDatabase {
            return LoginDatabase.getDatasetClient(context)
        }

        fun getWatchListOneJsonData(context: Context): WatchListOne? {
            val watchListOne = Helpers.getJsonDataFromAsset(
                context,
                context.getString(R.string.watchlist_1)
            )
            val watchList1Model: WatchListOne =
                gson.fromJson(watchListOne, WatchListOne::class.java)
            watchList1Model.WatchListOneData.forEachIndexed { position, item ->
                run {
                    item.Operator = "NULL"
                }
            }
            return watchList1Model
        }

        fun getWatchListSecondJsonData(context: Context): WatchListTwo? {
            val watchListOne = Helpers.getJsonDataFromAsset(
                context,
                context.getString(R.string.watchlist_2)
            )
            val watchList2Model: WatchListTwo =
                gson.fromJson(watchListOne, WatchListTwo::class.java)
            watchList2Model.WatchListTwoData.forEachIndexed { position, item ->
                run {
                    item.Operator = "NULL"
                }
            }
            return watchList2Model
        }

        fun getWatchListThreeJsonData(context: Context): WatchListThree? {
            val watchListOne = Helpers.getJsonDataFromAsset(
                context,
                context.getString(R.string.watchlist_3)
            )
            val watchList3Model: WatchListThree =
                gson.fromJson(watchListOne, WatchListThree::class.java)
            watchList3Model.WatchListThreeData.forEachIndexed { position, item ->
                run {
                    item.Operator = "NULL"
                }
            }
            return watchList3Model
        }


        fun insertWatchOneData(context: Context, watchListOneData: WatchListOneData) {
            loginDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                loginDatabase!!.loginDao().insertWatchListOne(watchListOneData)
            }
        }

        fun insertWatchTwoData(
            context: Context,
            watchListTwoData: com.mintu.assignment5paisa.models.watchlisttwo.WatchListTwoData
        ) {
            loginDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                loginDatabase!!.loginDao().insertWatchListTwo(watchListTwoData)
            }
        }

        fun insertWatchThreeData(
            context: Context,
            watchListThreeData: com.mintu.assignment5paisa.models.watchlistthree.WatchListThreeData
        ) {
            loginDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                loginDatabase!!.loginDao().insertWatchListThree(watchListThreeData)
            }
        }

        fun getWatchListOneData(context: Context): List<WatchListOneData> {
            loginDatabase = initializeDB(context)
            return loginDatabase!!.loginDao().getAllWatchListOneData()
        }

        fun getWatchListTwoData(context: Context): List<com.mintu.assignment5paisa.models.watchlisttwo.WatchListTwoData> {
            loginDatabase = initializeDB(context)
            return loginDatabase!!.loginDao().getAllWatchListTwoData()
        }

        fun getWatchListThreeData(context: Context): List<com.mintu.assignment5paisa.models.watchlistthree.WatchListThreeData> {
            loginDatabase = initializeDB(context)
            return loginDatabase!!.loginDao().getAllWatchListThreeData()
        }

        fun updateLastTradePriceWatchListThree(context: Context, price: Double, id: Int) {
            loginDatabase = initializeDB(context)
            return loginDatabase!!.loginDao().updateLastTradePriceWatchListThree(price, id)
        }

        fun updateLastTradePriceWatchListTwo(context: Context, price: Double, id: Int) {
            loginDatabase = initializeDB(context)
            return loginDatabase!!.loginDao().updateLastTradePriceWatchListTwo(price, id)
        }

        fun updateLastTradePriceWatchListOne(context: Context, price: Double, id: Int) {
            loginDatabase = initializeDB(context)
            return loginDatabase!!.loginDao().updateLastTradePriceWatchListOne(price, id)
        }

        fun updateLastOperatorWatchListThree(context: Context, operator: String, id: Int) {
            loginDatabase = initializeDB(context)
            return loginDatabase!!.loginDao().updateLastOperatorWatchListThree(operator, id)
        }

        fun updateLastOperatorWatchListTwo(context: Context, operator: String, id: Int) {
            loginDatabase = initializeDB(context)
            return loginDatabase!!.loginDao().updateLastOperatorWatchListTwo(operator, id)
        }

        fun updateLastOperatorWatchListOne(context: Context, operator: String, id: Int) {
            loginDatabase = initializeDB(context)
            return loginDatabase!!.loginDao().updateLastOperatorWatchListOne(operator, id)
        }
    }
}