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
import androidx.fragment.app.DialogFragment
import com.mm543.awis.R

class AboutDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            builder.setView(inflater.inflate(R.layout.dialog_about, null))
                .setPositiveButton(
                    R.string.ok
                ) { dialog, id ->
                    getDialog()?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}
