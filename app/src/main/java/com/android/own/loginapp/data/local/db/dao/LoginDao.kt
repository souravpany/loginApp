package com.android.own.loginapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.own.loginapp.data.local.db.entity.LoginEntity
import io.reactivex.Single

@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewLoginUser(loginEntity: LoginEntity): Single<Long>


}