package com.mm543.awis.database

interface ReadOnlyDao<R> {

    fun fetch(id: Int): R?

    fun fetchAll(page: Int, pageSize: Int): List<R>

}
