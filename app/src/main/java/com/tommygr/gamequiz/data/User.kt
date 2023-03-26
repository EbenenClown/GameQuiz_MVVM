package com.tommygr.gamequiz.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class User(@PrimaryKey val id: String, val displayName: String, val email: String, val dateJoined: Date)
