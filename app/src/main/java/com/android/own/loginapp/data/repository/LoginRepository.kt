package com.android.own.loginapp.data.repository

import com.android.own.loginapp.data.local.db.DatabaseService
import com.android.own.loginapp.data.local.db.entity.LoginEntity
import com.android.own.loginapp.data.local.prefs.UserPreferences
import com.android.own.loginapp.data.model.LoginUser
import com.android.own.loginapp.data.model.User
import io.reactivex.Single
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val databaseService: DatabaseService,
    private val userPreferences: UserPreferences
) {


    fun saveCurrentUser(user: LoginUser) {
        userPreferences.setUserId(user.id)
        userPreferences.setUserName(user.name)
        userPreferences.setUserEmailId(user.email)
    }

    fun getCurrentUser(): User? {

        val userId = userPreferences.getUserId()
        val userName = userPreferences.getUserName()
        val userEmailId = userPreferences.getUserEmailId()

        return if (userId !== null && userName != null && userEmailId != null)
            User(userId, userName, userEmailId)
        else
            null
    }


    fun addNewLoginUserToDatabase(
        name: String,
        email: String,
        password: String
    ): Single<Long> =
        databaseService.toDoDao()
            .insertNewLoginUser(
                LoginEntity(
                    name = name,
                    email = email,
                    password = password
                )
            )

}