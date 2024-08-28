package com.cdev.kmmsharedui.shared_preference

class MySharedPref(private val context: SPref? = null) {

    fun getInt(key: String, defaultValue: Int): Int {
        return context?.getInt(key, defaultValue) ?: defaultValue
    }

    fun putInt(key: String, value: Int) {
        context?.setInt(key, value)
    }

    fun getString(key: String, defaultValue: String): String {
        return context?.getString(key, defaultValue) ?: defaultValue
    }

    fun putString(key: String, value: String) {
        context?.setString(key, value)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return context?.getBoolean(key, defaultValue) ?: defaultValue
    }

    fun putBoolean(key: String, value: Boolean) {
        context?.setBoolean(key, value)
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return context?.getLong(key, defaultValue) ?: defaultValue
    }

    fun putLong(key: String, value: Long) {
        context?.setLong(key, value)
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return context?.getFloat(key, defaultValue) ?: defaultValue
    }

    fun putFloat(key: String, value: Float) {
        context?.setFloat(key, value)
    }
}