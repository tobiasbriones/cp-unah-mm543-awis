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

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mm543.awis.R
import com.mm543.awis.domain.model.Product
import com.mm543.awis.repository.AppProductRepository
import com.mm543.awis.ui.main.nav.NavDrawerAdapter
import com.mm543.awis.ui.main.nav.NavDrawerItem
import com.mm543.awis.ui.product.ProductActivity
import kotlinx.android.synthetic.main.fragment_product_search.*

class ProductSearchFragment : Fragment() {

    // Temporal implementation!
    private lateinit var products: List<Product>

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
        startProductActivity(products[position])
    }

    private fun setUpViews() {
        rv_search_products?.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        rv_search_products?.adapter = productSearchResultsAdapter

        productSearchResultsAdapter.setNavItemsData(prepareNavItems())
    }

    private fun prepareNavItems(): List<NavDrawerItem> {
        products = getRandomProducts()
        val menuItemList = ArrayList<NavDrawerItem>()

        products.forEach {
            menuItemList.add(
                NavDrawerItem(
                    it.productId,
                    it.name,
                    R.drawable.ic_launcher_background
                )
            )
        }
        return menuItemList
    }

    private fun getRandomProducts(): List<Product> {
        val repository = AppProductRepository()
        return repository.getAll(0, 10)
    }

    private fun startProductActivity(product: Product) {
        val intent = Intent(activity, ProductActivity::class.java)

        intent.putExtra("product", product)
        startActivity(intent)
    }

}

