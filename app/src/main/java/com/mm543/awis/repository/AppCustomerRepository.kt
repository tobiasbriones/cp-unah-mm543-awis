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
import com.mm543.awis.domain.model.Customer
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class AppCustomerRepository(
    private val context: Context
) {
    companion object {
        private const val CUSTOMER_FILE_NAME = "customer"
    }

    fun get(): Customer? {
        return loadCustomer()
    }

    fun set(customer: Customer?) {
        if (customer != null) {
            saveCustomer(customer)
        } else {
            deleteFile()
        }
    }

    private fun loadCustomer(): Customer? = try {
        context.openFileInput(CUSTOMER_FILE_NAME).use { read(it) }
    } catch (e: Exception) {
        null
    }

    private fun read(fis: FileInputStream): Customer {
        val ois = ObjectInputStream(fis)
        val customer: Customer

        ois.use { customer = ois.readObject() as Customer }
        return customer
    }

    private fun saveCustomer(customer: Customer) = try {
        context.openFileOutput(CUSTOMER_FILE_NAME, Context.MODE_PRIVATE).use { save(it, customer) }
    } catch (e: Exception) {
        val msg = "Fail to save cart"
        throw IOInternalStorageException(msg)
    }

    private fun save(fos: FileOutputStream?, cart: Customer) {
        val oo = ObjectOutputStream(fos)

        oo.writeObject(cart)
    }

    private fun deleteFile() {
        context.deleteFile(CUSTOMER_FILE_NAME)
    }
}
