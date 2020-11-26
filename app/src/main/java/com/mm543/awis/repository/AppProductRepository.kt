package com.mm543.awis.repository

import com.mm543.awis.domain.model.Product
import com.mm543.awis.domain.model.ProductModel
import com.mm543.awis.domain.repository.ProductRepository
import com.mm543.awis.domain.repository.ReadOnlyRepository
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class AppProductRepository : ProductRepository {
    // Mock data for now
    private val products = HashMap<Int, Product>()

    constructor() {
        initFakeData()
    }

    override fun get(id: Int): Product? {
        return products[id]
    }

    override fun getAll(page: Int, pageSize: Int): List<Product> {
        if (page < 0 || pageSize < 0) {
            val msg = "Page and Page Size are non-negative integers"
            throw  RuntimeException(msg)
        }
        val indexStart = page * pageSize
        val indexEnd = indexStart + pageSize
        return fetchRangeByCode(indexStart, indexEnd)
    }

    override fun searchByName(name: String): List<Product> {
        TODO("Not yet implemented")
    }

    private fun initFakeData() {
        for (i in 1..100) putRandomProduct()
    }

    private fun putRandomProduct() {
        val product = randomProduct()
        val id = product.productId

        products[id] = product
    }

    private fun randomProduct(): Product {
        val randomId = (Math.random() * 1000).toInt()
        return Product(
            randomId,
            randomProductModel(randomId),
            "Product $randomId",
            randomId,
            true,
            true,
            0,
            0,
            Math.random() * 1500,
            0.0
        )
    }

    private fun randomProductModel(randomId: Int): ProductModel {
        return ProductModel(
            randomId,
            "Product $randomId",
            "Description of product $randomId",
            "Instructions",
            LocalDateTime.now()
        )
    }

    private fun fetchRangeByCode(productCodeStart: Int, productCodeEnd: Int): List<Product> {
        val foundProducts = ArrayList<Product>(productCodeEnd - productCodeStart)

        for (productId in productCodeStart until productCodeEnd) {
            val product: Product? = products[productId]

            if (product != null) {
                foundProducts.add(product)
            }
        }
        return Collections.unmodifiableList(foundProducts)
    }

}
