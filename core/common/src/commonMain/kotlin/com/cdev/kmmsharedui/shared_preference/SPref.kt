package com.cdev.kmmsharedui.shared_preference

expect class SPref

expect fun SPref.getInt(key: String, defaultValue: Int) : Int
expect fun SPref.setInt(key: String, value: Int)
expect fun SPref.getString(key: String, defaultValue: String) : String
expect fun SPref.setString(key: String, value: String)
expect fun SPref.getBoolean(key: String, defaultValue: Boolean) : Boolean
expect fun SPref.setBoolean(key: String, value: Boolean)
expect fun SPref.getLong(key: String, defaultValue: Long) : Long
expect fun SPref.setLong(key: String, value: Long)
expect fun SPref.getFloat(key: String, defaultValue: Float) : Float
expect fun SPref.setFloat(key: String, value: Float)