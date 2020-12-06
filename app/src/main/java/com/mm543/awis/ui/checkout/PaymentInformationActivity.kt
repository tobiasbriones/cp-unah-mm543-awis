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

package com.mm543.awis.ui.checkout

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mm543.awis.R
import com.mm543.awis.repository.AppUserCreditCardRepository
import com.mm543.awis.repository.UserCreditCard
import kotlinx.android.synthetic.main.activity_payment_information.*

class PaymentInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_information)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        payment_add_credit_card_button.setOnClickListener { showAddCreditCardDialog() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> navigateBack()
        }
        return true
    }

    private fun onAddCreditCard(userCreditCard: UserCreditCard) {
        val creditCardRepository = AppUserCreditCardRepository()
        Log.d("APP", userCreditCard.toString())

        creditCardRepository.set(userCreditCard)
        showCreditCardAddedSuccessfullyDialog { navigateBack() }
    }

    private fun navigateBack() {
        onBackPressed()
    }

    private fun showAddCreditCardDialog() {
        val newFragment = AddCreditCardDialog { creditCard -> onAddCreditCard(creditCard) }
        newFragment.show(supportFragmentManager, "dialog")
    }

    private fun showCreditCardAddedSuccessfullyDialog(onAccept: () -> Unit) {
        val dialog = AlertDialog.Builder(this)
            .setMessage(R.string.credit_card_added_msg)
            .setPositiveButton(R.string.ok) { dialog, id ->
                onAccept()
            }.create()
        dialog.show()
    }
}
