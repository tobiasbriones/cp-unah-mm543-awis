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
import androidx.recyclerview.widget.RecyclerView
import com.mm543.awis.R
import com.mm543.awis.domain.model.Product
import com.mm543.awis.domain.model.ProductCategory
import com.mm543.awis.repository.AppProductRepository
import com.mm543.awis.ui.product.ProductActivity
import kotlinx.android.synthetic.main.fragment_product_search.*

class ProductSearchFragment : Fragment() {
    private val productRepository = AppProductRepository()
    private val productSearchAdapter: ProductSearchAdapter =
        ProductSearchAdapter { item ->
            onItemClick(item)
        }

    private lateinit var productsRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsRV = view.findViewById(R.id.product_search_rv)
        initViews()
        checkArguments()
    }

    fun searchProduct(query: String) {
        val results = productRepository.searchByName(query)
        setSearchResults(query, results)
    }

    private fun onItemClick(item: Product) {
        startProductActivity(item)
    }

    private fun checkArguments() {
        val categorySearch: ProductCategory? = arguments?.get("categorySearch") as ProductCategory?

        if (categorySearch != null) {
            searchByCategory(categorySearch)
        }
    }

    private fun setResults(title: String, results: List<Product>) {
        product_search_title_tv.text = title
        productSearchAdapter.setProducts(results)
    }

    private fun setSearchResults(query: String, results: List<Product>) {
        val resultsTitle = resources.getString(
            R.string.product_search_results_title
        ).format(query)

        setResults(resultsTitle, results)
    }

    private fun setSearchByCategoryResults(category: String, results: List<Product>) {
        val resultsTitle = resources.getString(
            R.string.product_by_category_results_title
        ).format(category)

        setResults(resultsTitle, results)
    }

    private fun initViews() {
        val orientation = RecyclerView.VERTICAL

        productsRV.layoutManager = LinearLayoutManager(activity, orientation, false)
        productsRV.adapter = productSearchAdapter

        productSearchAdapter.setProducts(getRandomProducts())
    }

    private fun getRandomProducts(): List<Product> {
        val repository = AppProductRepository()
        return repository.getAll(0, 10)
    }

    private fun searchByCategory(category: ProductCategory) {
        val results = productRepository.searchByCategory(category)
        setSearchByCategoryResults(category.name, results)
    }

    private fun startProductActivity(product: Product) {
        val intent = Intent(activity, ProductActivity::class.java)

        intent.putExtra("product", product)
        startActivity(intent)
    }
}
