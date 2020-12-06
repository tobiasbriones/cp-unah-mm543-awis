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
import com.mm543.awis.repository.AppProductRepository
import com.mm543.awis.ui.product.ProductActivity

class ProductSearchFragment : Fragment() {

    private val productSearchAdapter: ProductSearchAdapter = ProductSearchAdapter { position, item ->
        onItemClick(position, item)
    }

    private lateinit var productsRV: RecyclerView

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
        productsRV = view.findViewById(R.id.product_search_rv)
        initViews()
    }

    private fun onItemClick(position: Int, item: Product) {
        startProductActivity(item)
    }

    private fun initViews() {
        val orientation = RecyclerView.VERTICAL

        productsRV.layoutManager = LinearLayoutManager(activity, orientation, false)
        productsRV.adapter = productSearchAdapter

        productSearchAdapter.setProductItemsData(getRandomProducts())
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

