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
import com.mm543.awis.domain.model.shopping.CartItem
import kotlinx.android.synthetic.main.content_checkout.*


class CheckoutActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var productsAdapter: CartItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras

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
        TODO("Not yet implemented")
    }

    private fun initView() {
        user_name_tv.text = "User"
        total_products_tv.text = "-"
        total_price_tv.text = "-"

        initProductsRV()
    }

    private fun initProductsRV() {
        val recyclerView = findViewById<RecyclerView>(R.id.cart_products_rv)
        val input: ArrayList<CartItem> = ArrayList()
        layoutManager = LinearLayoutManager(this)
        productsAdapter = CartItemListAdapter(input)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = productsAdapter
        recyclerView.setHasFixedSize(true)

//        for (i in 0..9) {
//            input.add(C)
//        }


    }

    private fun navigateBack() {
        onBackPressed()
    }
}
