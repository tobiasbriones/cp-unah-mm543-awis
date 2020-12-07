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

package com.mm543.awis.service

import com.mm543.awis.domain.model.Customer

class CustomerLogInService {
    private val dummyPwd = "pwd"

    fun logIn(customer: Customer): Boolean {
        return customer.password == dummyPwd
    }
}
