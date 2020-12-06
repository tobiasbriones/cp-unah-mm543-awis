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

package com.mm543.awis.ui.product

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mm543.awis.R
import com.mm543.awis.domain.model.Product
import com.mm543.awis.domain.model.shopping.CartConstants
import com.mm543.awis.ui.checkout.CheckoutActivity
import kotlinx.android.synthetic.main.content_product.*

class ProductActivity : AppCompatActivity(), View.OnClickListener {
    private var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        product = extras?.getSerializable("product") as Product?

        setContentView(R.layout.activity_product)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> navigateBack()
            R.id.action_shopping_cart -> startCheckoutActivity()
        }
        return true
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    private fun getQuantity(): Int {
        val quantityStr = product_quantity_edit_text.text.toString()
        return try {
            quantityStr.toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }

    private fun setQuantity(quantity: Int) {
        if (quantity < 0) return
        val quantityStr = quantity.toString()
        product_quantity_edit_text.setText(quantityStr)
    }

    private fun initView() {
        setQuantity(CartConstants.DEF_PRODUCT_QUANTITY)
        product_name_text.text = product?.name
        product_color_text.text = product?.color.toString()
        product_price_text.text = product?.listPrice.toString()

        product_size_text.text = "Grande"
        product_weight_text.text = "100lb"
        increment_quantity_button.setOnClickListener { onIncrementQuantityButtonClick() }
        decrement_quantity_button.setOnClickListener { onDecrementQuantityButtonClick() }
        add_to_cart_button.setOnClickListener(this)
    }

    private fun startCheckoutActivity() {
        val intent = Intent(this, CheckoutActivity::class.java)

        startActivity(intent)
    }

    private fun onIncrementQuantityButtonClick() {
        val newQuantity = getQuantity() + 1
        setQuantity(newQuantity)
    }

    private fun onDecrementQuantityButtonClick() {
        val newQuantity = getQuantity() - 1
        setQuantity(newQuantity)
    }

    private fun navigateBack() {
        onBackPressed()
    }
}
