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

package com.mm543.awis

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.mm543.awis.domain.model.Customer
import com.mm543.awis.domain.model.ProductCategory
import com.mm543.awis.repository.AppCustomerRepository
import com.mm543.awis.service.CustomerLogInService
import com.mm543.awis.ui.checkout.CheckoutActivity
import com.mm543.awis.ui.checkout.PaymentInformationActivity
import com.mm543.awis.ui.main.AboutDialog
import com.mm543.awis.ui.main.LogInDialog
import com.mm543.awis.ui.main.categories.CategoriesFragment
import com.mm543.awis.ui.main.search.ProductSearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_user.*

// I have to implement MVVM later!
class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    SearchView.OnQueryTextListener {
    private lateinit var navigation: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var productSearchFragment: ProductSearchFragment
    private lateinit var productCategoriesFragment: CategoriesFragment
    private lateinit var currentFragment: Fragment
    private lateinit var menu: Menu
    private lateinit var navUserNameTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation = findViewById(R.id.main_drawer)
        navigationView = findViewById(R.id.activity_main_nav_view)
        productSearchFragment = ProductSearchFragment()
        productCategoriesFragment = CategoriesFragment()
        navUserNameTV = navigationView.getHeaderView(0).findViewById(R.id.main_nav_user_name_tv)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        navigationView.setNavigationItemSelectedListener(this)
        search_view.setOnQueryTextListener(this)
        fab.setOnClickListener { onSearchFabClick() }
        showProductsFragment()
        login()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        setMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                showNavigation()
                true
            }
            R.id.action_shopping_cart -> {
                startCheckoutActivity()
                true
            }
            R.id.action_settings -> {
                // TODO
                true
            }
            R.id.action_about -> {
                showAboutDialog()
                true
            }
            R.id.action_login -> {
                showLogInDialog()
                true
            }
            R.id.action_logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        closeDrawer()
        return when (item.itemId) {
            R.id.main_drawer_categories -> {
                showCategoriesFragment()
                true
            }
            R.id.main_drawer_payment -> {
                showPaymentInformationActivity()
                true
            }
            R.id.main_drawer_about -> {
                showAboutDialog()
                true
            }
            else -> true
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchProduct(query)
        }
        hideSearchView()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = true

    fun onProductCategoryItemClick(category: ProductCategory) {
        showProductsFragment(category)
    }

    private fun setMenu(menu: Menu?) {
        if (menu != null) {
            this.menu = menu
            setLogMenuItems()
        }
    }

    private fun setLogin(customer: Customer) {
        AppCustomerRepository(this).set(customer)
        login()
    }

    private fun setCustomerLogIn(customer: Customer?) {
        if (customer != null) {
            navUserNameTV.text = customer.firstName
        }
        else {
            navUserNameTV.text = "No user"
        }
        setLogMenuItems()
    }

    private fun onSearchFabClick() {
        showSearchWidget()
    }

    private fun onLogIn(credentials: LogInDialog.CustomerCredentials) {
        val customer = Customer(
            credentials.name,
            "",
            "",
            "",
            credentials.password
        )
        val isLoginValid = CustomerLogInService().logIn(customer)

        if (isLoginValid) {
            setLogin(customer)
        } else {
            showInvalidLoginDialog()
        }
    }

    private fun login() {
        val customer = AppCustomerRepository(this).get()
        setCustomerLogIn(customer)
    }

    private fun logout() {
        AppCustomerRepository(this).set(null)
        setCustomerLogIn(null)
    }

    private fun isUserLoggedIn(): Boolean {
        val customer = AppCustomerRepository(this).get()
        return customer != null && CustomerLogInService().logIn(customer)
    }

    private fun setLogMenuItems() {
        if (!this::menu.isInitialized) return
        val logInItem = menu.findItem(R.id.action_login)
        val logOutItem = menu.findItem(R.id.action_logout)

        if (isUserLoggedIn()) {
            logInItem.isVisible = false
            logOutItem.isVisible = true
        } else {
            logInItem.isVisible = true
            logOutItem.isVisible = false
        }
    }

    private fun showNavigation() {
        navigation.open()
    }

    private fun closeDrawer() {
        navigation.closeDrawer(Gravity.LEFT)
    }

    private fun showSearchWidget() {
        search_view.visibility = View.VISIBLE
        search_view.isIconified = false
        fab.visibility = View.INVISIBLE
    }

    private fun hideSearchView() {
        search_view.visibility = View.GONE
        fab.visibility = View.VISIBLE
    }

    private fun showLogInDialog() {
        val newFragment = LogInDialog { credentials -> onLogIn(credentials) }
        newFragment.show(supportFragmentManager, "dialog")
    }

    private fun showInvalidLoginDialog() {
        val dialog = AlertDialog.Builder(this)
            .setMessage(R.string.invalid_login_msg)
            .setPositiveButton(R.string.ok) { dialog, which -> showLogInDialog() }
        dialog.show()
    }

    private fun showAboutDialog() {
        val newFragment = AboutDialog()
        newFragment.show(supportFragmentManager, "dialog")
    }

    private fun startCheckoutActivity() {
        val intent = Intent(this, CheckoutActivity::class.java)

        startActivity(intent)
    }

    private fun showPaymentInformationActivity() {
        val intent = Intent(this, PaymentInformationActivity::class.java)

        startActivity(intent)
    }

    private fun showProductsFragment() {
        val fm: FragmentManager = supportFragmentManager
        val ft = fm.beginTransaction()

        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .replace(R.id.main_content_frame, productSearchFragment)
            .commit()
        currentFragment = productSearchFragment
    }

    private fun showProductsFragment(category: ProductCategory) {
        val fm: FragmentManager = supportFragmentManager
        val ft = fm.beginTransaction()
        val bundle = Bundle()

        bundle.putSerializable("categorySearch", category)
        productSearchFragment.arguments = bundle
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .replace(R.id.main_content_frame, productSearchFragment)
            .commit()
        currentFragment = productSearchFragment
    }

    private fun showCategoriesFragment() {
        val fm: FragmentManager = supportFragmentManager
        val ft = fm.beginTransaction()

        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .replace(R.id.main_content_frame, productCategoriesFragment)
            .commit()
        currentFragment = productCategoriesFragment
    }

    private fun searchProduct(query: String) {
        productSearchFragment.searchProduct(query)
    }
}
