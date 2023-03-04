package com.mintu.assignment5paisa.utils

import android.annotation.SuppressLint
import android.content.Context
import com.mintu.assignment5paisa.models.watchlistone.WatchListOneData
import com.mintu.assignment5paisa.models.watchlistthree.WatchListThreeData
import com.mintu.assignment5paisa.models.watchlisttwo.WatchListTwoData
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class Helpers {
    companion object {
        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

        fun formatVolume(vol: Int): String {
            if (vol < 1000) return "" + vol
            var volume: Float = vol.toFloat()
            var exp: String = ""
            if (Math.abs(vol / 10000000.0) >= 1) {
                volume = Math.abs(vol / 1000000.0).toFloat()
                exp = "C"
            } else if (Math.abs(vol / 100000.0) >= 1) {
                volume = Math.abs(vol / 100000.0).toFloat()
                exp = "L"
            } else if (Math.abs(vol / 1000.0) >= 1) {
                volume = Math.abs(vol / 1000.0).toFloat()
                exp = "K"
            }
            return String.format("%.1f $exp", volume)
        }

        @SuppressLint("SimpleDateFormat")
        fun formatTime(time: String): String {
            val r1 = time.replace("/Date(", "")
            val r2 = r1.replace(")/", "")
            val r3 = r2.replace("+0530", "")
            val long = r3.toLong()
            return try {
                val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm")
                val netDate = Date(long)
                sdf.format(netDate)
            } catch (e: Exception) {
                r3
            }
        }

        /**
         * Sort By Volume Helpers
         */
        fun sortListByVol1Asc(
            list: ArrayList<WatchListOneData>
        ): List<WatchListOneData> {
            return list.sortedBy { it.Volume }
        }

        fun sortListByVol1Dsc(
            list: ArrayList<WatchListOneData>
        ): List<WatchListOneData> {
            return list.sortedByDescending { it.Volume }
        }

        fun sortListByVol2Asc(
            list: ArrayList<WatchListTwoData>
        ): List<WatchListTwoData> {
            return list.sortedBy { it.Volume }
        }

        fun sortListByVol2Dsc(
            list: ArrayList<WatchListTwoData>
        ): List<WatchListTwoData> {
            return list.sortedByDescending { it.Volume }
        }

        fun sortListByVol3Asc(
            list: ArrayList<WatchListThreeData>
        ): List<WatchListThreeData> {
            return list.sortedBy { it.Volume }
        }

        fun sortListByVol3Dsc(
            list: ArrayList<WatchListThreeData>
        ): List<WatchListThreeData> {
            return list.sortedByDescending { it.Volume }
        }

        /**
         * Sort By PClose Helpers
         */
        fun sortListByPClose1Asc(
            list: ArrayList<WatchListOneData>
        ): List<WatchListOneData> {
            return list.sortedBy { it.PClose }
        }

        fun sortListByPClose1Dsc(
            list: ArrayList<WatchListOneData>
        ): List<WatchListOneData> {
            return list.sortedByDescending { it.PClose }
        }

        fun sortListByPClose2Asc(
            list: ArrayList<WatchListTwoData>
        ): List<WatchListTwoData> {
            return list.sortedBy { it.PClose }
        }

        fun sortListByPClose2Dsc(
            list: ArrayList<WatchListTwoData>
        ): List<WatchListTwoData> {
            return list.sortedByDescending { it.PClose }
        }

        fun sortListByPClose3Asc(
            list: ArrayList<WatchListThreeData>
        ): List<WatchListThreeData> {
            return list.sortedBy { it.PClose }
        }

        fun sortListByPClose3Dsc(
            list: ArrayList<WatchListThreeData>
        ): List<WatchListThreeData> {
            return list.sortedByDescending { it.PClose }
        }

        /**
         * Sort By LastTradeTime Helpers
         */
        fun sortListByLastTradeTime1Asc(
            list: ArrayList<WatchListOneData>
        ): List<WatchListOneData> {
            return list.sortedBy { it.LastTradeTime }
        }

        fun sortListByLastTradeTime1Dsc(
            list: ArrayList<WatchListOneData>
        ): List<WatchListOneData> {
            return list.sortedByDescending { it.LastTradeTime }
        }

        fun sortListByLastTradeTime2Asc(
            list: ArrayList<WatchListTwoData>
        ): List<WatchListTwoData> {
            return list.sortedBy { it.LastTradeTime }
        }

        fun sortListByLastTradeTime2Dsc(
            list: ArrayList<WatchListTwoData>
        ): List<WatchListTwoData> {
            return list.sortedByDescending { it.LastTradeTime }
        }

        fun sortListByLastTradeTime3Asc(
            list: ArrayList<WatchListThreeData>
        ): List<WatchListThreeData> {
            return list.sortedBy { it.LastTradeTime }
        }

        fun sortListByLastTradeTime3Dsc(
            list: ArrayList<WatchListThreeData>
        ): List<WatchListThreeData> {
            return list.sortedByDescending { it.LastTradeTime }
        }

        fun randomOperatorName(): String {
            val list = listOf("+", "-")
            val randomIndex = Random.nextInt(list.size);
            return list[randomIndex]
        }

        fun randomNumber(): Int {
            val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            val randomIndex = Random.nextInt(list.size);
            return list[randomIndex]
        }

    }
}