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

package com.mm543.awis.ui.main.nav

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mm543.awis.R
import kotlinx.android.synthetic.main.nav_drawer_item.view.*

class NavDrawerAdapter(private val onItemClick: ((position: Int, item: NavDrawerItem) -> Unit)) :
    RecyclerView.Adapter<NavDrawerAdapter.SideNavVH>() {
    var menuItemsList = ArrayList<NavDrawerItem>()

    fun setNavItemsData(list: List<NavDrawerItem>) {
        menuItemsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SideNavVH {
        return SideNavVH(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.nav_drawer_item, viewGroup, false)
        )
    }

    inner class SideNavVH(inflate: View) : RecyclerView.ViewHolder(inflate) {
        fun setData(navDrawerItem: NavDrawerItem) {
            itemView.setOnClickListener {
                navDrawerItem.let {
                    onItemClick.invoke(adapterPosition, navDrawerItem)
                }
            }
            itemView.iv_nav_option?.setImageResource(navDrawerItem.resourceId)
            itemView.tv_nav_text?.text = navDrawerItem.itemName
        }
    }

    override fun getItemCount(): Int {
        return menuItemsList.size
    }

    override fun onBindViewHolder(holder: SideNavVH, position: Int) {
        holder.setData(menuItemsList[position])
    }
}
