package com.cdev.kmmsharedui.shared_preference

import android.app.Application
import android.content.Context.MODE_PRIVATE

actual typealias SPref = Application

actual fun SPref.getInt(key: String, defaultValue: Int): Int {
    val prefs = this.getSharedPreferences("", MODE_PRIVATE)
    return prefs.getInt(key, defaultValue)
}


actual fun SPref.setInt(key: String, value: Int) {
    val prefs = this.getSharedPreferences("", MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putInt(key, value)
    editor.apply()
}

actual fun SPref.getString(key: String, defaultValue: String): String {
    val prefs = this.getSharedPreferences("", MODE_PRIVATE)
    return prefs.getString(key, defaultValue) ?: defaultValue
}


actual fun SPref.setString(key: String, value: String) {
    val prefs = this.getSharedPreferences("", MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putString(key, value)
    editor.apply()
}

actual fun SPref.getBoolean(key: String, defaultValue: Boolean): Boolean {
    val prefs = this.getSharedPreferences("", MODE_PRIVATE)
    return prefs.getBoolean(key, defaultValue)
}


actual fun SPref.setBoolean(key: String, value: Boolean) {
    val prefs = this.getSharedPreferences("", MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putBoolean(key, value)
    editor.apply()
}

actual fun SPref.getLong(key: String, defaultValue: Long): Long {
    val prefs = this.getSharedPreferences("", MODE_PRIVATE)
    return prefs.getLong(key, defaultValue)
}


actual fun SPref.setLong(key: String, value: Long) {
    val prefs = this.getSharedPreferences("", MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putLong(key, value)
    editor.apply()
}

actual fun SPref.getFloat(key: String, defaultValue: Float): Float {
    val prefs = this.getSharedPreferences("", MODE_PRIVATE)
    return prefs.getFloat(key, -1f)
}


actual fun SPref.setFloat(key: String, value: Float) {
    val prefs = this.getSharedPreferences("", MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putFloat(key, value)
    editor.apply()
}