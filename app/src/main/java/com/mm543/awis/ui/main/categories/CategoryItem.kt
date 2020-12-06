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

package com.mm543.awis.ui.main.categories

import com.mm543.awis.domain.model.ProductCategory

data class CategoryItem(
    val productCategory: ProductCategory,
    val imageId: Int
) {
    val name: String = productCategory.name
}
