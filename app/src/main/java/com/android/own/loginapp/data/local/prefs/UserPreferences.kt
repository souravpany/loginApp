package com.android.own.loginapp.data.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_USER_EMAIL_ID = "PREF_KEY_USER_EMAIL_ID"
        const val KEY_USER_NAME = "PREF_KEY_USER_NAME"
        const val KEY_USER_ID = "PREF_KEY_USER_ID"

    }

    fun getUserId(): Long? =
        prefs.getLong(KEY_USER_ID, 0)

    fun setUserId(userId: Long) =
        prefs.edit().putLong(KEY_USER_EMAIL_ID, userId).apply()

    fun getUserEmailId(): String? =
        prefs.getString(KEY_USER_ID, null)

    fun setUserEmailId(userId: String) =
        prefs.edit().putString(KEY_USER_EMAIL_ID, userId).apply()

    fun getUserName(): String? =
        prefs.getString(KEY_USER_NAME, null)

    fun setUserName(userName: String) =
        prefs.edit().putString(KEY_USER_NAME, userName).apply()


}