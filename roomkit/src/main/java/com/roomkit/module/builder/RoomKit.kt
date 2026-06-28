package com.roomkit.module.builder

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Room

object RoomKit {

    // Main build function — Class reference se
    fun <T : RoomDatabase> build(
        context: Context,
        databaseClass: Class<T>,
        databaseName: String,
        fallbackToDestructiveMigration: Boolean = true
    ): T {
        return Room.databaseBuilder(
            context.applicationContext,
            databaseClass,
            databaseName
        ).apply {
            if (fallbackToDestructiveMigration) {
                fallbackToDestructiveMigration()
            }
        }.build()
    }

    // Inline function — Type safe shortcut
    inline fun <reified T : RoomDatabase> build(
        context: Context,
        databaseName: String,
        fallbackToDestructiveMigration: Boolean = true
    ): T = build(
        context,
        T::class.java,
        databaseName,
        fallbackToDestructiveMigration
    )
}