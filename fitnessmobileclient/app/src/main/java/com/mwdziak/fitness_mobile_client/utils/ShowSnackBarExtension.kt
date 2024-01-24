package com.mwdziak.fitness_mobile_client.utils

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mwdziak.fitness_mobile_client.R

fun Fragment.showSnackBar(message: String, error: Boolean) {
    val snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
    snackbar.setTextColor(
        ContextCompat.getColor(
            requireContext(),
            R.color.white
        )
    )
    if (error) {
        snackbar.setBackgroundTint(
            ContextCompat.getColor(
                requireContext(),
                R.color.snackBarError
            )
        )
    } else {
        snackbar.setBackgroundTint(
            ContextCompat.getColor(
                requireContext(),
                R.color.snackBarSuccessful
            )
        )
    }
    snackbar.show()
}