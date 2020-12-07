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
import java.util.function.Consumer

object CartConstants {
    const val DEF_PRODUCT_QUANTITY: Int = 1
}

class Cart : Serializable, Iterable<CartItem> {
    private val items = ArrayList<CartItem>()
    val totalItems: Int = items.size
    val totalPrice: Double get() = computeTotalPrice()

    override fun iterator(): Iterator<CartItem> = items.iterator()

    fun add(item: CartItem) {
        if (items.contains(item)) return
        items.add(item)
    }

    fun findItemOf(product: Product): CartItem? {
        var item: CartItem? = null

        for (current in items) {
            if (current.product == product) {
                item = current
                break
            }
        }
        return item
    }

    fun remove(item: CartItem) {
        items.remove(item)
    }

    private fun computeTotalPrice(): Double {
        var price = 0.0

        items.forEach(Consumer { price += it.price })
        return price
    }
}

class CartItem(
    val product: Product,
    var quantity: Int = CartConstants.DEF_PRODUCT_QUANTITY
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
