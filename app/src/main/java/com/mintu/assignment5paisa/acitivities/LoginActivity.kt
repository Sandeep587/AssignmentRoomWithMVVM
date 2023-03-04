package com.mintu.assignment5paisa.acitivities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mintu.assignment5paisa.R
import com.mintu.assignment5paisa.viewModels.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private var mContext: Context = this
    private lateinit var btnLogin: Button
    private lateinit var btnGetDetail: Button
    private lateinit var edtname: EditText
    private lateinit var edtpass: EditText
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        if (loginViewModel.checkLogIn(mContext)) {
            val intent = Intent(mContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin = findViewById(R.id.btnLogin)
        edtname = findViewById(R.id.edtUserName)
        edtpass = findViewById(R.id.edtPassword)

        btnLogin.setOnClickListener {
            loginViewModel.insertData(
                mContext,
                edtname.text.toString(),
                edtpass.text.toString(),
                true
            )
            val intent = Intent(mContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}