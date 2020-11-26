package com.mm543.awis.domain.repository

interface ReadOnlyRepository<R> {
    fun get(id: Int): R?
    fun getAll(page: Int, pageSize: Int): List<R>
}
