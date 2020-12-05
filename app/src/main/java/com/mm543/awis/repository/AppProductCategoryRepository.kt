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

import com.mm543.awis.domain.model.ProductCategory
import com.mm543.awis.domain.repository.ProductCategoryRepository

class AppProductCategoryRepository : ProductCategoryRepository {
    private val inMemoryCategories = ArrayList<ProductCategory>()

    init {
        inMemoryCategories.add(ProductCategory(1, "Cat A"))
        inMemoryCategories.add(ProductCategory(2, "Cat B"))
        inMemoryCategories.add(ProductCategory(3, "Cat C"))
    }

    override fun get(id: Int): ProductCategory? {
        TODO("Not yet implemented")
    }

    override fun getAll(page: Int, pageSize: Int): List<ProductCategory> {
        return inMemoryCategories
    }
}
