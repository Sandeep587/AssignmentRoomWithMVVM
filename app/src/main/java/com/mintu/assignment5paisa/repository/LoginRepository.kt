package com.mintu.assignment5paisa.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.mintu.assignment5paisa.models.LoginTableModel
import com.mintu.assignment5paisa.room.LoginDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginRepository {
    companion object{
        var loginDatabase:LoginDatabase?=null
        var loginTableModel: LiveData<LoginTableModel>?=null

        private fun initializeDB(context: Context) : LoginDatabase {
            return LoginDatabase.getDatasetClient(context)
        }

        fun insertData(context: Context, username: String, password: String, isLogin:Boolean) {

            loginDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = LoginTableModel(username, password,isLogin)
                loginDatabase!!.loginDao().insertData(loginDetails)
            }
        }

        fun  checkLogin(context: Context):Boolean{
            loginDatabase = initializeDB(context)
            var islogin=false
            islogin= loginDatabase?.loginDao()?.getAll()?.isLogin == true
            return islogin
        }
        fun getLoginDetails(context: Context, username: String):LiveData<LoginTableModel>?{
            loginDatabase = initializeDB(context)
            loginTableModel = loginDatabase!!.loginDao().getLoginDetails(username)
            return loginTableModel
        }
    }
}