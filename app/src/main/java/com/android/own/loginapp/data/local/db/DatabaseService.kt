package com.android.own.loginapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.own.loginapp.data.local.db.dao.LoginDao
import com.android.own.loginapp.data.local.db.entity.LoginEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        LoginEntity::class

    ],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun toDoDao(): LoginDao

}