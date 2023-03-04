package com.mintu.assignment5paisa.acitivities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.mintu.assignment5paisa.R
import com.mintu.assignment5paisa.WatchListsModel
import com.mintu.assignment5paisa.adapters.TabsAdapter
import com.mintu.assignment5paisa.databinding.ActivityMainBinding
import com.mintu.assignment5paisa.interfaces.ApplyTheme
import com.mintu.assignment5paisa.utils.Constants
import com.mintu.assignment5paisa.utils.Helpers
import com.mintu.assignment5paisa.utils.SharedPreference

class MainActivity : AppCompatActivity(), ApplyTheme {
    private lateinit var binding: ActivityMainBinding
    private val items = arrayOf("Light", "Dark", "System Default")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val theme = SharedPreference.get(applicationContext, Constants.THEME)
        changeTheme(theme!!)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabsJsonString = Helpers.getJsonDataFromAsset(
            applicationContext, applicationContext.resources
                .getString(R.string.watchlist_names)
        )
        val tabsList = Gson().fromJson(tabsJsonString, WatchListsModel::class.java)

        val tabsAdapter = TabsAdapter(tabsList, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = tabsAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

    }

    override fun apply() {
        selectThemeDialog()
    }

    private fun changeTheme(theme: String) {
        SharedPreference.save(applicationContext, Constants.THEME, theme)
        when (theme) {
            "Light" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
            "Dark" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
            "System Default" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
            else -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
        }
    }

    private fun selectThemeDialog() {
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle("Select Theme")
            setItems(items) { dialog, which ->
                changeTheme(items[which])
            }
            setPositiveButton("") { dialogInterface, _ ->
                run {
                    dialogInterface.dismiss()
                }
            }
            show()
        }
    }
}