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

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mm543.awis.MainActivity
import com.mm543.awis.R
import com.mm543.awis.ui.user.UserActivity
import kotlinx.android.synthetic.main.fragment_nav_drawer.*

class NavDrawerFragment : Fragment(), View.OnClickListener {

    private val navDrawerAdapter: NavDrawerAdapter = NavDrawerAdapter { position, item ->
        onItemClick(position, item)
    }

    private fun onItemClick(position: Int, item: NavDrawerItem) {
        Toast.makeText(requireContext(), "" + item.itemName, Toast.LENGTH_LONG).show()
        (activity as MainActivity).closeDrawer(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_nav_drawer, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.user -> startUserActivity()
        }
    }

    private fun setUpViews() {
        items_recycler?.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        items_recycler?.adapter = navDrawerAdapter

        user_name_tv.text = "User X"
        user.setOnClickListener(this)
        navDrawerAdapter.setNavItemsData(prepareNavItems())
    }

    private fun prepareNavItems(): List<NavDrawerItem> {
        val menuItemsList = ArrayList<NavDrawerItem>()

        menuItemsList.add(NavDrawerItem(1, "Categorías", R.drawable.ic_baseline_category_24))
        menuItemsList.add(NavDrawerItem(2, "Puntos de ventas", R.drawable.ic_baseline_store_24))
        menuItemsList.add(NavDrawerItem(3, "Notificaciones", R.drawable.ic_baseline_notifications_24))
        menuItemsList.add(NavDrawerItem(5, "Método de pago", R.drawable.ic_baseline_payment_24))
        menuItemsList.add(NavDrawerItem(6, "Configuración", R.drawable.ic_baseline_settings_24))
        menuItemsList.add(NavDrawerItem(7, "Sobre AWIS", R.drawable.ic_baseline_info_24))
        return menuItemsList
    }

    private fun startUserActivity() {
        val intent = Intent(activity, UserActivity::class.java)

        startActivity(intent)
    }

}
