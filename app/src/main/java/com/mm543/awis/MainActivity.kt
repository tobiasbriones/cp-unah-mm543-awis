package com.mm543.awis

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.mm543.awis.ui.main.nav.NavDrawerItem

class MainActivity : AppCompatActivity() {

    lateinit var navigation: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation = findViewById(R.id.drawer)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater

        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sign_in -> {
                // TODO
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun closeDrawer(item: NavDrawerItem) {
        navigation.closeDrawer(Gravity.LEFT)
    }

}
