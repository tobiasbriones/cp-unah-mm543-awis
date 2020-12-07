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
import com.mm543.awis.domain.model.shopping.Cart
import com.mm543.awis.domain.model.shopping.CartItem
import com.mm543.awis.repository.AppCartRepository
import com.mm543.awis.repository.IOInternalStorageException
import kotlinx.android.synthetic.main.content_checkout.*

class CheckoutActivity : AppCompatActivity(),
    CartItemListAdapter.OnRemoveItemListener {
    private val cartRepository = AppCartRepository(this)
    private lateinit var cart: Cart
    private lateinit var productsAdapter: CartItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cart = cartRepository.get()
        productsAdapter = CartItemListAdapter(this)

        setContentView(R.layout.activity_checkout)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        initView()
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
        showPayDialog()
    }

    private fun onSaveCartFailed(msg: String) {
        cart = cartRepository.get()

        showInfoDialog(msg)
        update()
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
        main_nav_user_name_tv.text = "User"
        total_products_tv.text = "-"
        total_price_tv.text = "$-"
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

    private fun saveCart() {
        try {
            cartRepository.set(cart)
        } catch (e: IOInternalStorageException) {
            e.message?.let { onSaveCartFailed(it) }
        }
    }
}
