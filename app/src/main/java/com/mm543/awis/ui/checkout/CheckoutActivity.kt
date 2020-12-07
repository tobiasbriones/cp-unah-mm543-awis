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

package com.mm543.awis.ui.checkout

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mm543.awis.R
import com.mm543.awis.domain.model.Customer
import com.mm543.awis.domain.model.shopping.Cart
import com.mm543.awis.domain.model.shopping.CartItem
import com.mm543.awis.repository.AppCartRepository
import com.mm543.awis.repository.AppCustomerRepository
import com.mm543.awis.repository.IOInternalStorageException
import com.mm543.awis.service.CustomerLogInService
import com.mm543.awis.ui.main.LogInDialog
import kotlinx.android.synthetic.main.content_checkout.*

class CheckoutActivity : AppCompatActivity(),
                         CartItemListAdapter.OnRemoveItemListener {
    private val cartRepository = AppCartRepository(this)
    private lateinit var cart: Cart
    private lateinit var productsAdapter: CartItemListAdapter
    private var customer: Customer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cart = cartRepository.get()
        productsAdapter = CartItemListAdapter(this)

        setContentView(R.layout.activity_checkout)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        initView()
        login()
        update()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> navigateBack()
        }
        return true
    }

    override fun onCartItemRemoved(item: CartItem) {
        cart.remove(item)
        saveCart()
        updateResumeInfo()
    }

    private fun onNextButtonClick() {
        if (customer == null) {
            showLogInDialog()
            return
        }
        showPayDialog()
    }

    private fun onSaveCartFailed(msg: String) {
        cart = cartRepository.get()

        showInfoDialog(msg)
        update()
    }

    private fun onLogIn(credentials: LogInDialog.CustomerCredentials) {
        val customer = Customer(
            credentials.name,
            "",
            "",
            "",
            credentials.password
        )
        val isLoginValid = CustomerLogInService().logIn(customer)

        if (isLoginValid) {
            setLogin(customer)
        }
        else {
            showInvalidLoginDialog()
        }
    }

    private fun setCustomer(value: Customer?) {
        customer = value

        updateUserInfo()
    }

    private fun setLogin(customer: Customer) {
        AppCustomerRepository(this).set(customer)
        login()
    }

    private fun initView() {
        checkout_next_button.setOnClickListener { onNextButtonClick() }
        initProductsRV()
    }

    private fun initProductsRV() {
        val recyclerView = findViewById<RecyclerView>(R.id.cart_products_rv)
        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = productsAdapter
        recyclerView.setHasFixedSize(true)
    }

    private fun update() {
        updateUserInfo()
        updateProductsRV()
        updateResumeInfo()
    }

    private fun updateUserInfo() {
        val cust = customer

        if (cust != null) {
            main_nav_user_name_tv.text = cust.firstName
        }
        else {
            main_nav_user_name_tv.text = "No user"
        }
    }

    private fun updateProductsRV() {
        cart.forEachIndexed { index, cartItem -> productsAdapter.add(index, cartItem) }
    }

    private fun updateResumeInfo() {
        total_products_tv.text = cart.totalItems.toString()
        total_price_tv.text = cart.totalPrice.toString()
    }

    private fun navigateBack() {
        onBackPressed()
    }

    private fun showInfoDialog(msg: String) {
        val dialog = AlertDialog.Builder(this)
            .setMessage(msg)
            .setPositiveButton(R.string.ok, null)
            .create()

        dialog.show()
    }

    private fun showPayDialog() {
        val newFragment = PayWithCreditCardDialog()
        newFragment.show(supportFragmentManager, "dialog")
    }

    private fun showLogInDialog() {
        val newFragment = LogInDialog { credentials -> onLogIn(credentials) }
        newFragment.show(supportFragmentManager, "dialog")
    }

    private fun showInvalidLoginDialog() {
        val dialog = AlertDialog.Builder(this)
            .setMessage(R.string.invalid_login_msg)
            .setPositiveButton(R.string.ok) { dialog, which -> showLogInDialog() }
        dialog.show()
    }

    private fun saveCart() {
        try {
            cartRepository.set(cart)
        }
        catch (e: IOInternalStorageException) {
            e.message?.let { onSaveCartFailed(it) }
        }
    }

    private fun login() {
        val customer = AppCustomerRepository(this).get()

        if (customer != null) {
            val isValidLogin = CustomerLogInService().logIn(customer)

            if (isValidLogin) {
                setCustomer(customer)
            }
        }
    }
}
