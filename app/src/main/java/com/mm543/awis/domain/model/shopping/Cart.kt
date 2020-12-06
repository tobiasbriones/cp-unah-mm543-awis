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

object CartConstants {
    const val DEF_PRODUCT_QUANTITY: Int = 1
}

class Cart : Serializable, Iterable<CartItem> {
    private val items = ArrayList<CartItem>()
    private var totalPrice = 0.0

    override fun iterator(): Iterator<CartItem> = items.iterator()

    fun totalItems(): Int = items.size
    fun totalPrice(): Double = String.format("%.2f", totalPrice).toDouble()

    fun add(item: CartItem) {
        items.add(item)
        onItemAdded(item)
    }

    fun remove(item: CartItem) {
        val itemRemoved = items.remove(item)

        if (itemRemoved) onItemRemoved(item)
    }

    fun setItemAt(oldItem: CartItem, newItem: CartItem) {
        val index = items.indexOf(oldItem)

        if (index != -1) {
            items[index] = newItem

            onItemRemoved(oldItem)
            onItemAdded(newItem)
        }
    }

    private fun onItemAdded(item: CartItem) {
        totalPrice += item.price()
    }

    private fun onItemRemoved(item: CartItem) {
        totalPrice -= item.price()
    }
}

data class CartItem(
    val product: Product,
    val quantity: Int = CartConstants.DEF_PRODUCT_QUANTITY
) : Serializable {
    init {
        validate()
    }

    fun name(): String = product.name
    fun price(): Double = product.listPrice * quantity

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
