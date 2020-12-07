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

import android.content.Context
import com.mm543.awis.domain.model.shopping.Cart
import com.mm543.awis.domain.repository.CartRepository
import java.io.*

class AppCartRepository(
    private val context: Context
) : CartRepository {
    companion object {
        private const val CART_FILE_NAME = "shopping_cart"
    }

    override fun get(): Cart {
        return loadCart()
    }

    override fun set(cart: Cart) {
        saveCart(cart)
    }

    private fun loadCart(): Cart = try {
        context.openFileInput(CART_FILE_NAME).use { read(it) }
    }
    catch (e: FileNotFoundException) {
        Cart()
    }
    catch (e: Exception) {
        val msg = "Fail to open user shopping cart"
        throw IOInternalStorageException(msg)
    }

    private fun read(fis: FileInputStream): Cart {
        val ois = ObjectInputStream(fis)
        val cart: Cart

        ois.use { cart = ois.readObject() as Cart }
        return cart
    }

    private fun saveCart(cart: Cart) = try {
        context.openFileOutput(CART_FILE_NAME, Context.MODE_PRIVATE).use { save(it, cart) }
    }
    catch (e: Exception) {
        val msg = "Fail to save cart"
        throw IOInternalStorageException(msg)
    }

    private fun save(fos: FileOutputStream?, cart: Cart) {
        val oo = ObjectOutputStream(fos)

        oo.writeObject(cart)
    }
}
