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

package com.mm543.awis.domain.usecase

import com.mm543.awis.domain.model.Product
import com.mm543.awis.domain.model.shopping.Cart
import com.mm543.awis.domain.model.shopping.CartItem
import com.mm543.awis.domain.repository.CartRepository

class AddProductToCartUseCase(
    private val cartRepository: CartRepository
) {
    fun execute(product: Product, quantity: Int) {
        val cart = cartRepository.get()
        val cartItem = getCartItem(cart, product)
        cartItem.quantity = quantity

        cart.add(cartItem)
        cartRepository.set(cart)
    }

    private fun getCartItem(cart: Cart, product: Product): CartItem {
        return cart.findItemOf(product) ?: CartItem(product)
    }
}
