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

import com.mm543.awis.domain.model.shopping.Cart
import com.mm543.awis.domain.model.shopping.CartItem

class CartItemSetQuantityUseCase(
    private val cart: Cart
) {
    fun execute(item: CartItem, quantity: Int) {
        val newItem = CartItem(item.product, quantity)
        cart.setItemAt(item, newItem)
    }
}
