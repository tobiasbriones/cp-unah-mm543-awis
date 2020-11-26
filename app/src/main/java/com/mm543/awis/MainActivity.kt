package com.mm543.awis

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import com.mm543.awis.ui.main.AboutDialog
import com.mm543.awis.ui.main.SignInDialog
import com.mm543.awis.ui.main.nav.NavDrawerItem


class MainActivity : AppCompatActivity() {

    private lateinit var navigation: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation = findViewById(R.id.drawer)

        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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

    private fun showSignInDialog() {
        val newFragment: DialogFragment = SignInDialog()
        newFragment.show(supportFragmentManager, "dialog")
    }

    private fun showAboutDialog() {
        val newFragment: AboutDialog = AboutDialog()
        newFragment.show(supportFragmentManager, "dialog")
    }

    fun closeDrawer(item: NavDrawerItem) {
        navigation.closeDrawer(Gravity.LEFT)
    }

}
