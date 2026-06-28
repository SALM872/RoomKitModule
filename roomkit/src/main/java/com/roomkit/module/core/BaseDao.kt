package com.roomkit.module.core

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BaseDao<T : BaseEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<T>): List<Long>

    @Update
    suspend fun update(entity: T)

    @Delete
    suspend fun delete(entity: T)

    @Delete
    suspend fun deleteAll(entities: List<T>)
}