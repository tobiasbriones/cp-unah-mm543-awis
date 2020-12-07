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

package com.mm543.awis.ui.user

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mm543.awis.R
import com.mm543.awis.domain.model.Customer
import com.mm543.awis.repository.AppCustomerRepository
import kotlinx.android.synthetic.main.content_user.*

class UserActivity : AppCompatActivity() {
    private lateinit var customer: Customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        customer = getCustomer()

        setContentView(R.layout.activity_user)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        update()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.user, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> navigateBack()
            R.id.action_save -> save()
        }
        return true
    }

    private fun getCustomer(): Customer {
        val customer = AppCustomerRepository(this).get()

        if (customer == null) {
            finish()
        }
        return customer!!
    }

    private fun update() {
        main_nav_user_name_tv.text = customer.firstName
        user_name_edit_text.setText(customer.firstName)
        email_address_edit_text.setText(customer.emailAddress)
    }

    private fun navigateBack() {
        onBackPressed()
    }

    private fun save() {
        // TODO
    }
}
