package com.mintu.assignment5paisa.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mintu.assignment5paisa.models.LoginTableModel
import com.mintu.assignment5paisa.models.watchlistone.WatchListOneData

@Database(entities = [LoginTableModel::class,WatchListOneData::class,com.mintu.assignment5paisa.models.watchlisttwo.WatchListTwoData::class,com.mintu.assignment5paisa.models.watchlistthree.WatchListThreeData::class], version = 1, exportSchema = false)
public abstract class LoginDatabase: RoomDatabase() {
    abstract fun loginDao() : DAOAccess

    companion object{

        @Volatile
        private var INSTANCE: LoginDatabase? = null

        fun getDatasetClient(context: Context) : LoginDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, LoginDatabase::class.java, "LOGIN_DATABASE")
                    .fallbackToDestructiveMigration().
                     allowMainThreadQueries()
                    .build()

                return INSTANCE!!
            }
        }
    }
}