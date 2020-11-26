package com.mm543.awis.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mm543.awis.MainActivity
import com.mm543.awis.R
import com.mm543.awis.ui.main.nav.NavDrawerAdapter
import com.mm543.awis.ui.main.nav.NavDrawerItem
import kotlinx.android.synthetic.main.fragment_product_search.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProductSearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private val productSearchResultsAdapter: NavDrawerAdapter = NavDrawerAdapter { position, item ->
        onItemClick(position, item)
    }

    private fun onItemClick(position: Int, item: NavDrawerItem) {
        Toast.makeText(requireContext(), "" + item.itemName, Toast.LENGTH_LONG).show()
        (activity as MainActivity).closeDrawer(item)
    }

    private fun setUpViews() {
        rv_search_products?.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        rv_search_products?.adapter = productSearchResultsAdapter

        productSearchResultsAdapter.setNavItemsData(prepareNavItems())
    }

    private fun prepareNavItems(): List<NavDrawerItem> {
        val menuItemsList = ArrayList<NavDrawerItem>()

        menuItemsList.add(NavDrawerItem(1, "Producto A", R.drawable.ic_launcher_background))
        menuItemsList.add(NavDrawerItem(2, "Producto B", R.drawable.ic_launcher_background))
        menuItemsList.add(NavDrawerItem(3, "Producto C", R.drawable.ic_launcher_background))
        menuItemsList.add(NavDrawerItem(4, "Producto D", R.drawable.ic_launcher_background))
        menuItemsList.add(NavDrawerItem(5, "Producto E", R.drawable.ic_launcher_background))
        menuItemsList.add(NavDrawerItem(6, "Producto F", R.drawable.ic_launcher_background))
        menuItemsList.add(NavDrawerItem(7, "Producto G", R.drawable.ic_launcher_background))
        menuItemsList.add(NavDrawerItem(8, "Producto H", R.drawable.ic_launcher_background))
        menuItemsList.add(NavDrawerItem(9, "Producto I", R.drawable.ic_launcher_background))
        return menuItemsList
    }
}

