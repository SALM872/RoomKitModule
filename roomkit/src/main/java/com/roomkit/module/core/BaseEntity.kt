package com.roomkit.module.core

import androidx.room.PrimaryKey

abstract class BaseEntity {
    @PrimaryKey(autoGenerate = true)
    open var id: Long = 0
}
