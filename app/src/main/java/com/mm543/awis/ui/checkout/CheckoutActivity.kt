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
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mm543.awis.R
import com.mm543.awis.domain.model.Product
import com.mm543.awis.domain.model.ProductModel
import com.mm543.awis.domain.model.shopping.Cart
import com.mm543.awis.domain.model.shopping.CartItem
import kotlinx.android.synthetic.main.content_checkout.*
import java.time.LocalDateTime

class CheckoutActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var cart: Cart
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var productsAdapter: CartItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        cart = sampleCart()

        setContentView(R.layout.activity_checkout)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        initView()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> navigateBack()
        }
        return true
    }

    override fun onClick(v: View?) {
        showPayDialog()
    }

    private fun initView() {
        user_name_tv.text = "User"
        total_products_tv.text = "-"
        total_price_tv.text = "$-"

        checkout_next_button.setOnClickListener(this)
        initProductsRV()
        initResumeInfo()
    }

    private fun initResumeInfo() {
        total_products_tv.text = cart.totalItems().toString()
        total_price_tv.text = cart.totalPrice().toString()
    }

    private fun initProductsRV() {
        val recyclerView = findViewById<RecyclerView>(R.id.cart_products_rv)
        val input: ArrayList<CartItem> = ArrayList()
        layoutManager = LinearLayoutManager(this)
        productsAdapter = CartItemListAdapter(input)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = productsAdapter
        recyclerView.setHasFixedSize(true)

        addCartProductsToRV()
    }

    private fun addCartProductsToRV() {
        cart.forEachIndexed { index, cartItem -> productsAdapter.add(index, cartItem) }
    }

    private fun sampleCart(): Cart {
        val cart = Cart()
        cart.add(sampleItem())
        cart.add(sampleItem())
        return cart
    }

    private fun sampleItem(): CartItem {
        return CartItem(
            Product(
                1,
                ProductModel(
                    1,
                    "Name",
                    "Catalog",
                    "Ins",
                    LocalDateTime.now()
                ),
                "Name",
                1,
                true,
                true,
                1,
                1,
                23.4,
                232.4
            ),
            2
        )
    }

    private fun navigateBack() {
        onBackPressed()
    }

    private fun showPayDialog() {
        val newFragment = PayWithCreditCardDialog()
        newFragment.show(supportFragmentManager, "dialog")
    }

}
