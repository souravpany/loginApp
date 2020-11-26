package com.android.own.loginapp.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "login_table")
data class LoginEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "Name")
    val name: String,

    @ColumnInfo(name = "Email")
    val email: String,


    @ColumnInfo(name = "password")
    val password: String
) {
    constructor() : this(0, "", "", "")
}