package com.roomkit.module.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseRepository<T : BaseEntity>(
    private val dao: BaseDao<T>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun insert(entity: T): Long =
        withContext(dispatcher) { dao.insert(entity) }

    suspend fun insertAll(entities: List<T>): List<Long> =
        withContext(dispatcher) { dao.insertAll(entities) }

    suspend fun update(entity: T) =
        withContext(dispatcher) { dao.update(entity) }

    suspend fun delete(entity: T) =
        withContext(dispatcher) { dao.delete(entity) }

    suspend fun deleteAll(entities: List<T>) =
        withContext(dispatcher) { dao.deleteAll(entities) }
}