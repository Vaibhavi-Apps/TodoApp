package com.vppanchalofficial.reminder

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity

class PreferenceHelper(context: FragmentActivity) {
    val sharedPreferences : SharedPreferences = context.getSharedPreferences("sharedPreference",Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    fun saveString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
        editor.commit()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, "");
    }

    fun saveInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
        editor.commit()
    }
    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0);
    }

    fun getBooleanData(key: String?): Boolean {
        return sharedPreferences.getBoolean(key,false)
    }

    fun getBooleanDataTrue(key: String?): Boolean {
        return sharedPreferences.getBoolean(key,true)
    }

    fun saveBooleanData(key: String?, value: Boolean) {
        val prefsEditor: SharedPreferences.Editor = sharedPreferences.edit()
        prefsEditor.putBoolean(key, value)
        prefsEditor.apply()
    }
    fun clearPreference() {
       editor.clear()
        editor.apply()
    }

}
