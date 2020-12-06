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

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.mm543.awis.R
import com.mm543.awis.repository.UserCreditCard

class AddCreditCardDialog(
    private val onAddCreditCard: ((creditCard: UserCreditCard) -> Unit)
) : DialogFragment() {
    private lateinit var cardNumberET: EditText
    private lateinit var cardExpirationET: EditText
    private lateinit var cardCodeET: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_pay_with_credit_card, null)
        cardNumberET = view.findViewById(R.id.card_number_edit_text)
        cardExpirationET = view.findViewById(R.id.card_expiration_edit_text)
        cardCodeET = view.findViewById(R.id.card_code)

        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setView(view)
                .setPositiveButton(R.string.add) { dialog, id ->
                    onAddCreditCard(getCreditCard())
                }
                .setNegativeButton(R.string.back) { dialog, id ->
                    getDialog()?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun getCreditCard(): UserCreditCard {
        val cardNumber = getCardNumber()
        val cardExpiration = getCardExpiration()
        val cardCode = getCardCode()
        return UserCreditCard(
            cardNumber,
            cardExpiration,
            cardCode
        )
    }

    private fun getCardNumber(): Int {
        val cardNumberStr = cardNumberET.text.toString()
        return try {
            cardNumberStr.toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }

    private fun getCardExpiration(): String {
        return cardExpirationET.text.toString()
    }

    private fun getCardCode(): Int {
        val cardCodeStr = cardCodeET.text.toString()
        return try {
            cardCodeStr.toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }
}
