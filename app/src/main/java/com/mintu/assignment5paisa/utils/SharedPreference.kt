package com.mintu.assignment5paisa.utils

import android.content.Context
import com.mintu.assignment5paisa.R

class SharedPreference {

    companion object {
        fun save(context: Context, key: String, value: String) {
            val sharedPreference = context.getSharedPreferences(
                context
                    .getString(R.string.preference_file_key), Context.MODE_PRIVATE
            )
            var editor = sharedPreference.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun saveBool(context: Context, key: String, value: Boolean) {
            val sharedPreference = context.getSharedPreferences(
                context
                    .getString(R.string.preference_file_key), Context.MODE_PRIVATE
            )
            var editor = sharedPreference.edit()
            editor.putBoolean(key, value)
            editor.apply()
        }

        fun get(context: Context, key: String): String? {
            val sharedPreference = context.getSharedPreferences(
                context
                    .getString(R.string.preference_file_key), Context.MODE_PRIVATE
            )
            return sharedPreference.getString(key, "not_found")
        }

        fun getBool(context: Context, key: String): Boolean? {
            val sharedPreference = context.getSharedPreferences(
                context
                    .getString(R.string.preference_file_key), Context.MODE_PRIVATE
            )
            return sharedPreference.getBoolean(key, false)
        }

        fun remove(context: Context, key: String) {
            val sharedPreference = context.getSharedPreferences(
                context
                    .getString(R.string.preference_file_key), Context.MODE_PRIVATE
            )
            var editor = sharedPreference.edit()
            editor.remove(key)
            editor.apply()
        }

        fun clear(context: Context, key: String) {
            val sharedPreference = context.getSharedPreferences(
                context
                    .getString(R.string.preference_file_key), Context.MODE_PRIVATE
            )
            var editor = sharedPreference.edit()
            editor.clear()
            editor.apply()
        }
    }
}