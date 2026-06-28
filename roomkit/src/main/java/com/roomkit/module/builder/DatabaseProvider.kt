package com.roomkit.module.builder

import android.content.Context
import androidx.room.RoomDatabase
import kotlin.reflect.KClass

object DatabaseProvider {

    private val instances = mutableMapOf<String, RoomDatabase>()

    @Suppress("UNCHECKED_CAST")
    fun <T : RoomDatabase> getInstance(
        context: Context,
        databaseClass: Class<T>,
        databaseName: String,
        fallbackToDestructiveMigration: Boolean = true
    ): T {
        return instances.getOrPut(databaseName) {
            RoomKit.build(
                context,
                databaseClass,
                databaseName,
                fallbackToDestructiveMigration
            )
        } as T
    }

    inline fun <reified T : RoomDatabase> getInstance(
        context: Context,
        databaseName: String,
        fallbackToDestructiveMigration: Boolean = true
    ): T = getInstance(
        context,
        T::class.java,
        databaseName,
        fallbackToDestructiveMigration
    )

    fun closeAll() {
        instances.values.forEach { it.close() }
        instances.clear()
    }

    fun close(databaseName: String) {
        instances[databaseName]?.close()
        instances.remove(databaseName)
    }
}