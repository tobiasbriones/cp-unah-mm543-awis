package com.mm543.awis.ui.main.nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mm543.awis.MainActivity
import com.mm543.awis.R
import kotlinx.android.synthetic.main.fragment_nav_drawer.*

class NavDrawerFragment : Fragment() {

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

    private fun setUpViews() {
        rv_side_nav_options?.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        rv_side_nav_options?.adapter = navDrawerAdapter

        tv_nav_user.text = "User X"
        navDrawerAdapter.setNavItemsData(prepareNavItems())
    }

    private fun prepareNavItems(): List<NavDrawerItem> {
        val menuItemsList = ArrayList<NavDrawerItem>()

        menuItemsList.add(NavDrawerItem(1, "Categorías", R.drawable.ic_launcher_background))
        menuItemsList.add(NavDrawerItem(2, "Puntos de ventas", R.drawable.ic_launcher_background))
        menuItemsList.add(NavDrawerItem(3, "Notificaciones", R.drawable.ic_launcher_background))
        menuItemsList.add(NavDrawerItem(4, "Cuenta", R.drawable.ic_launcher_background))
        menuItemsList.add(NavDrawerItem(5, "Método de pago", R.drawable.ic_launcher_background))
        menuItemsList.add(NavDrawerItem(6, "Configuración", R.drawable.ic_launcher_background))
        menuItemsList.add(NavDrawerItem(7, "Sobre AWIS", R.drawable.ic_launcher_background))
        return menuItemsList
    }

}
