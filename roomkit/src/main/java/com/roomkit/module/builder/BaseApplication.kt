package com.roomkit.module.builder

import android.app.Application
import androidx.room.RoomDatabase

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: BaseApplication

        fun getContext(): android.content.Context = instance.applicationContext
    }
}