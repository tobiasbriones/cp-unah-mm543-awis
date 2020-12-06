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

package com.mm543.awis.ui.main.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mm543.awis.R
import com.mm543.awis.domain.model.Product
import kotlinx.android.synthetic.main.product_search_item.view.*

class ProductSearchAdapter(private val onItemClick: ((item: Product) -> Unit)) :
    RecyclerView.Adapter<ProductSearchAdapter.ViewHolder>() {
    private val productItems = ArrayList<Product>()

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val li = LayoutInflater.from(viewGroup.context)
        return ViewHolder(
            li.inflate(R.layout.product_search_item, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return productItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(productItems[position])
    }

    fun setProducts(items: List<Product>) {
        silentClear()
        productItems.addAll(items)
        notifyDataSetChanged()
    }

    private fun silentClear() {
        productItems.clear()
    }

    inner class ViewHolder(inflate: View) : RecyclerView.ViewHolder(inflate) {
        fun setData(productItem: Product) {
            itemView.setOnClickListener {
                productItem.let {
                    onItemClick.invoke(productItem)
                }
            }
            itemView.product_image_iv.setImageResource(R.drawable.ic_baseline_info_24)
            itemView.product_name_tv.text = productItem.name
        }
    }
}
