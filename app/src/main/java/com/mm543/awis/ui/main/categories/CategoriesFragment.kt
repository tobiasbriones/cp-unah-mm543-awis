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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mm543.awis.R
import com.mm543.awis.domain.model.ProductCategory
import com.mm543.awis.repository.AppProductCategoryRepository
import kotlinx.android.synthetic.main.fragment_product_search.*

class CategoriesFragment : Fragment() {

    private val categoriesAdapter: CategoriesAdapter = CategoriesAdapter { position, item ->
        onItemClick(position, item)
    }

    private lateinit var categoriesRV: RecyclerView
    private lateinit var categories: List<ProductCategory>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoriesRV = view.findViewById(R.id.categories_rv)
        initViews(view)
    }

    private fun onItemClick(position: Int, item: CategoryItem) {
    }

    private fun initViews(view: View) {
        initCategoriesRV()
    }

    private fun initCategoriesRV() {
        val activity = requireActivity()

        categoriesRV.layoutManager = GridLayoutManager(activity, 2)
        categoriesRV.adapter = categoriesAdapter

        categoriesAdapter.setCategoryItemsData(prepareCategoryItems())
    }

    private fun prepareCategoryItems(): List<CategoryItem> {
        categories = getRandomCategories()
        val menuItemList = ArrayList<CategoryItem>()

        categories.forEach {
            menuItemList.add(
                CategoryItem(
                    it.name,
                    R.drawable.ic_launcher_background
                )
            )
        }
        return menuItemList
    }

    private fun getRandomCategories(): List<ProductCategory> {
        val repository = AppProductCategoryRepository()
        return repository.getAll(0, 10)
    }

}

