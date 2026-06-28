package com.roomkit.module.builder

import android.content.Context
import androidx.room.RoomDatabase

object RoomKitConfig {

    private var appContext: Context? = null
    private var instance: RoomDatabase? = null
    private var databaseName: String = "roomkit_db"

    fun <T : RoomDatabase> init(
        context: Context,
        databaseClass: Class<T>,
        databaseName: String = "roomkit_db",
        fallbackToDestructiveMigration: Boolean = true
    ) {
        appContext = context.applicationContext
        this.databaseName = databaseName
        instance = DatabaseProvider.getInstance(
            context,
            databaseClass,
            databaseName,
            fallbackToDestructiveMigration
        )
    }

    inline fun <reified T : RoomDatabase> init(
        context: Context,
        databaseName: String = "roomkit_db",
        fallbackToDestructiveMigration: Boolean = true
    ) = init(context, T::class.java, databaseName, fallbackToDestructiveMigration)

    @Suppress("UNCHECKED_CAST")
    fun <T : RoomDatabase> db(): T {
        return instance as? T
            ?: throw IllegalStateException(
                "RoomKitConfig not initialized! Call RoomKitConfig.init() in Application class first."
            )
    }

    fun getContext(): Context {
        return appContext
            ?: throw IllegalStateException(
                "RoomKitConfig not initialized! Call RoomKitConfig.init() in Application class first."
            )
    }

    fun isInitialized(): Boolean = instance != null

    fun destroy() {
        DatabaseProvider.closeAll()
        instance = null
        appContext = null
    }
}