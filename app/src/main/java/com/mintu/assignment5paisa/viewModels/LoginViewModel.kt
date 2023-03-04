package com.mintu.assignment5paisa.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mintu.assignment5paisa.models.LoginTableModel
import com.mintu.assignment5paisa.repository.LoginRepository

class LoginViewModel : ViewModel() {
    var liveDataLogin: LiveData<LoginTableModel>? = null

    fun insertData(context: Context, username: String, password: String, isLogin: Boolean) {
        LoginRepository.insertData(context, username, password, isLogin)
    }

    fun checkLogIn(context: Context): Boolean {
        return LoginRepository.checkLogin(context)
    }

    fun getLoginDetails(context: Context, username: String): LiveData<LoginTableModel>? {
        liveDataLogin = LoginRepository.getLoginDetails(context, username)
        return liveDataLogin
    }

}