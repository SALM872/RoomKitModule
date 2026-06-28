package com.roomkit.module.core

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object SafeMigration {

    fun create(
        startVersion: Int,
        endVersion: Int,
        migrate: (SupportSQLiteDatabase) -> Unit
    ): Migration {
        return object : Migration(startVersion, endVersion) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    migrate(database)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun createAll(
        vararg migrations: Migration
    ): Array<Migration> = arrayOf(*migrations)
}