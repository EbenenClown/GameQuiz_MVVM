package com.tommygr.gamequiz.data.source.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user")
data class UserDataModel(@PrimaryKey val userId: String, val userName: String = "", val email: String = "", val isRegistered: Boolean = false)
