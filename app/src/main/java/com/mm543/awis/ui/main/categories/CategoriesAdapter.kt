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

package com.mm543.awis.ui.main.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mm543.awis.R
import com.mm543.awis.ui.main.categories.CategoriesAdapter.ViewHolder
import kotlinx.android.synthetic.main.product_category_item.view.*

class CategoriesAdapter(private val onItemClick: ((position: Int, item: CategoryItem) -> Unit)) :
    RecyclerView.Adapter<ViewHolder>() {
    private val categoryItems = ArrayList<CategoryItem>()

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val li = LayoutInflater.from(viewGroup.context)
        return ViewHolder(
            li.inflate(R.layout.product_category_item, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return categoryItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(categoryItems[position])
    }

    fun setCategoryItemsData(items: List<CategoryItem>) {
        categoryItems.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(inflate: View) : RecyclerView.ViewHolder(inflate) {
        fun setData(categoryItem: CategoryItem) {
            itemView.setOnClickListener {
                categoryItem.let {
                    onItemClick.invoke(adapterPosition, categoryItem)
                }
            }
            itemView.product_category_image_iv?.setImageResource(categoryItem.imageId)
            itemView.product_category_name_tv?.text = categoryItem.name
        }
    }
}
