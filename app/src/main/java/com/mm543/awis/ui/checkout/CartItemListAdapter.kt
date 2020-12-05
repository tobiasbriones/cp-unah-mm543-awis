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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mm543.awis.R
import com.mm543.awis.domain.model.shopping.CartItem

class CartItemListAdapter(private val values: ArrayList<CartItem>) :
    RecyclerView.Adapter<CartItemListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val removeButton: ImageButton = view.findViewById(R.id.remove_item_button)
        val quantityTV: TextView = view.findViewById(R.id.item_quantity_tv)
        val photoIV: ImageView = view.findViewById(R.id.product_photo_iv)
        val nameTV: TextView = view.findViewById(R.id.product_name_tv)
        val priceTV: TextView = view.findViewById(R.id.product_price_tv)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.checkout_cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.quantityTV.text = item.quantity.toString()
        holder.nameTV.text = item.product.name
        holder.priceTV.text = item.price().toString()
        holder.removeButton.setOnClickListener { onRemoveButtonClick(item) }
    }

    override fun getItemCount(): Int {
        return values.size
    }

    fun add(position: Int, item: CartItem) {
        values.add(position, item)
        notifyItemInserted(position)
    }

    fun remove(position: Int) {
        values.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun onRemoveButtonClick(item: CartItem) {
        remove(values.indexOf(item))
    }
}
