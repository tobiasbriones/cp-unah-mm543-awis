package com.mm543.awis.domain.repository

import com.mm543.awis.domain.model.Product

interface ProductRepository : ReadOnlyRepository<Product> {
    fun searchByName(name: String): List<Product>
}
