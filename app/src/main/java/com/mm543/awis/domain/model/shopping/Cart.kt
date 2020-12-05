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

class Cart() : Serializable {
    private val items = ArrayList<CartItem>()
    private var totalPrice = 0.0

    fun totalItems(): Int = items.size
    fun totalPrice(): Double = totalPrice

    fun add(item: CartItem) {
        items.add(item)
        totalPrice += item.price()
    }

    fun remove(item: CartItem) {
        val itemRemoved = items.remove(item)

        if (itemRemoved) onItemRemoved()
    }

    private fun onItemRemoved() = totalPrice--
}

data class CartItem(
    private val product: Product,
    val quantity: Int
) : Serializable {
    init {
        if (quantity < 0) {
            val msg = "Quantity is a non-negative integer number: $quantity"
            throw RuntimeException(msg)
        }
    }

    fun name(): String = product.name
    fun price(): Double = product.listPrice
}
