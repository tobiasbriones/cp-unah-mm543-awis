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

package com.mm543.awis.domain.model.shopping

import com.mm543.awis.domain.model.Product
import java.io.Serializable

class CartItem(
    val product: Product,
    var quantity: Int = Cart.DEF_PRODUCT_QUANTITY
) : Serializable {
    val name: String = product.name
    val price: Double = product.listPrice * quantity

    init {
        validate()
    }

    private fun validate() {
        validateQuantity()
    }

    private fun validateQuantity() {
        if (quantity < 0) {
            val msg = "Quantity is a non-negative integer number: $quantity"
            throw RuntimeException(msg)
        }
    }
}
