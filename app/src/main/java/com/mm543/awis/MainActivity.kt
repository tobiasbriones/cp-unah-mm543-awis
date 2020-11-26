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

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import com.mm543.awis.ui.main.AboutDialog
import com.mm543.awis.ui.main.PayWithCreditCardDialog
import com.mm543.awis.ui.main.SignInDialog
import com.mm543.awis.ui.main.nav.NavDrawerItem


class MainActivity : AppCompatActivity() {

    private lateinit var navigation: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation = findViewById(R.id.drawer)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                showNavigation()
                true
            }
            R.id.action_shopping_cart -> {
                // TODO
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
                showSignInDialog()
                true
            }
            R.id.action_logout -> {
                // TODO
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showNavigation() {
        navigation.open()
    }

    private fun showSignInDialog() {
        val newFragment = SignInDialog()
        newFragment.show(supportFragmentManager, "dialog")
    }

    private fun showAboutDialog() {
        val newFragment = AboutDialog()
        newFragment.show(supportFragmentManager, "dialog")
    }

    private fun showPayDialog() {
        val newFragment = PayWithCreditCardDialog()
        newFragment.show(supportFragmentManager, "dialog")
    }

    fun closeDrawer(item: NavDrawerItem) {
        navigation.closeDrawer(Gravity.LEFT)
    }

}
