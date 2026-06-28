package com.roomkit.module.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

open class BaseViewModel<T : BaseEntity>(
    private val dao: BaseDao<T>
) : ViewModel() {

    fun insert(entity: T) {
        viewModelScope.launch {
            dao.insert(entity)
        }
    }

    fun insertAll(entities: List<T>) {
        viewModelScope.launch {
            dao.insertAll(entities)
        }
    }

    fun update(entity: T) {
        viewModelScope.launch {
            dao.update(entity)
        }
    }

    fun delete(entity: T) {
        viewModelScope.launch {
            dao.delete(entity)
        }
    }

    fun deleteAll(entities: List<T>) {
        viewModelScope.launch {
            dao.deleteAll(entities)
        }
    }
}