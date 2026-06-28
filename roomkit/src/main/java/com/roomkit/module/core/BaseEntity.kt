package com.roomkit.module.core

import androidx.room.PrimaryKey

abstract class BaseEntity {
    @PrimaryKey(autoGenerate = true)
    open val id: Long = 0
}