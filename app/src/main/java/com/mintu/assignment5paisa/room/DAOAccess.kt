package com.mintu.assignment5paisa.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mintu.assignment5paisa.models.LoginTableModel
import com.mintu.assignment5paisa.models.watchlistone.WatchListOneData

@Dao
interface DAOAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(loginTableModel: LoginTableModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchListOne(watchListOneData: WatchListOneData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchListTwo(watchListOneWatchListTwoData: com.mintu.assignment5paisa.models.watchlisttwo.WatchListTwoData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchListThree(watchListOneWatchListThreeData: com.mintu.assignment5paisa.models.watchlistthree.WatchListThreeData)

    @Query("SELECT * FROM Login WHERE Username =:username")
    fun getLoginDetails(username: String?) : LiveData<LoginTableModel>

    @Query("SELECT * FROM LOGIN")
    fun getAll(): LoginTableModel

    @Query("SELECT * FROM WatchListOne")
    fun getAllWatchListOneData(): List<WatchListOneData>

    @Query("SELECT * FROM WatchListTwo")
    fun getAllWatchListTwoData(): List<com.mintu.assignment5paisa.models.watchlisttwo.WatchListTwoData>

    @Query("SELECT * FROM WatchListThree")
    fun getAllWatchListThreeData(): List<com.mintu.assignment5paisa.models.watchlistthree.WatchListThreeData>

    @Query("UPDATE WatchListOne SET LastTradePrice=:price WHERE id=:id")
    fun updateLastTradePriceWatchListOne(price: Double, id: Int)

    @Query("UPDATE WatchListTwo SET LastTradePrice=:price WHERE id=:id")
    fun updateLastTradePriceWatchListTwo(price: Double, id: Int)

    @Query("UPDATE WatchListThree SET LastTradePrice=:price WHERE id=:id")
    fun updateLastTradePriceWatchListThree(price: Double, id: Int)

    @Query("UPDATE WatchListOne SET Operator=:operator WHERE id=:id")
    fun updateLastOperatorWatchListOne(operator: String, id: Int)

    @Query("UPDATE WatchListTwo SET Operator=:operator WHERE id=:id")
    fun updateLastOperatorWatchListTwo(operator: String, id: Int)

    @Query("UPDATE WatchListThree SET Operator=:operator WHERE id=:id")
    fun updateLastOperatorWatchListThree(operator: String, id: Int)

}