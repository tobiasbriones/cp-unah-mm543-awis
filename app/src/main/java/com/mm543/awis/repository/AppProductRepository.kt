/*
 * AWIS: Adventure Works Information Systems, Android App
 * Created for the MM543-0900-3-2020 course.
 * Team: Tobias Briones, Juan Varela
 *
 * Copyright (c) 2020 Tobias Briones.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.mm543.awis.repository

import com.mm543.awis.domain.model.Product
import com.mm543.awis.domain.model.ProductCategory
import com.mm543.awis.domain.model.ProductModel
import com.mm543.awis.domain.repository.ProductRepository
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class AppProductRepository : ProductRepository {

    // Mock data for now
    private val products = HashMap<Int, Product>()

    init {
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
        // TODO implement
        // Dummy sample:
        products.clear()
        initFakeData()
        return getAll(0, 10)
    }

    override fun searchByCategory(category: ProductCategory): List<Product> {
        TODO("Not yet implemented")
    }

    private fun initFakeData() {
        for (i in 1..100) putProduct(randomProduct())
    }

    private fun putProduct(product: Product) {
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

    private fun fetchRangeByCode(pageStartIndex: Int, pageEndIndex: Int): List<Product> {
        val size = pageEndIndex - pageStartIndex
        val productSortedKeys = productsSortedKeys()
        val foundProducts = ArrayList<Product>(size)

        for (pos in pageStartIndex until pageEndIndex) {
            if (pos >= productSortedKeys.size) continue
            val product: Product? = products[productSortedKeys[pos]]

            if (product != null) {
                foundProducts.add(product)
            }
        }
        return Collections.unmodifiableList(foundProducts)
    }

    private fun productsSortedKeys(): List<Int> {
        return products.keys.sorted()
    }

}
