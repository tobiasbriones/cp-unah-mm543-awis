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

package com.mm543.awis.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.mm543.awis.R

class LogInDialog(
    private val onLogIn: ((credentials: CustomerCredentials) -> Unit)
) : DialogFragment() {

    data class CustomerCredentials(
        val name: String,
        val password: String
    )

    private lateinit var userNameET: EditText
    private lateinit var passwordET: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_login, null)
        userNameET = view.findViewById(R.id.customer_login_username_et)
        passwordET = view.findViewById(R.id.customer_login_password_et)

        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setView(view)
                .setPositiveButton(
                    R.string.signin
                ) { dialog, id ->
                    onLogIn(
                        CustomerCredentials(
                            getUsername(),
                            getPassword()
                        )
                    )
                }
                .setNegativeButton(
                    R.string.cancel
                ) { dialog, id ->
                    getDialog()?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun getUsername(): String {
        return userNameET.text.toString()
    }

    private fun getPassword(): String {
        return passwordET.text.toString()
    }
}
