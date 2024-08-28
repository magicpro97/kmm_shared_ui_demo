package com.cdev.kmmsharedui.shared_preference

import platform.Foundation.NSUserDefaults
import platform.darwin.NSObject

actual typealias SPref = NSObject

actual fun SPref.getInt(key: String, defaultValue: Int): Int {
    return getValue(key, defaultValue) { k -> NSUserDefaults.standardUserDefaults.integerForKey(k).toInt() }
}

actual fun SPref.setInt(key: String, value: Int) {
    NSUserDefaults.standardUserDefaults.setInteger(value.toLong(), key)
}

actual fun SPref.getString(key: String, defaultValue: String): String {
    return getValue(key, defaultValue) { k -> NSUserDefaults.standardUserDefaults.stringForKey(k) ?: defaultValue }
}

actual fun SPref.setString(key: String, value: String) {
    NSUserDefaults.standardUserDefaults.setString(value, key)
}

actual fun SPref.getBoolean(key: String, defaultValue: Boolean): Boolean {
    return getValue(key, defaultValue) { k -> NSUserDefaults.standardUserDefaults.boolForKey(k) }
}

actual fun SPref.setBoolean(key: String, value: Boolean) {
    NSUserDefaults.standardUserDefaults.setBool(value, key)
}

actual fun SPref.getLong(key: String, defaultValue: Long): Long {
    return getValue(key, defaultValue) { k -> NSUserDefaults.standardUserDefaults.integerForKey(k) }
}

actual fun SPref.setLong(key: String, value: Long) {
    NSUserDefaults.standardUserDefaults.setInteger(value, key)
}

actual fun SPref.getFloat(key: String, defaultValue: Float): Float {
    return getValue(key, defaultValue) { k -> NSUserDefaults.standardUserDefaults.floatForKey(k) }
}

actual fun SPref.setFloat(key: String, value: Float) {
    NSUserDefaults.standardUserDefaults.setFloat(value, key)
}

private inline fun <T> getValue(key: String, defaultValue: T, callback: (String) -> T): T {
    return try {
        callback(key)
    } catch (e: Exception) {
        e.printStackTrace()
        defaultValue
    }
}