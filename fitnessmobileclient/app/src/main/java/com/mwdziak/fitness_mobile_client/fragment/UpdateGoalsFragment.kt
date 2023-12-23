package com.mwdziak.fitness_mobile_client.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.viewmodel.RegisterViewModel
import com.mwdziak.fitness_mobile_client.viewmodel.UpdateGoalsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateGoalsFragment : Fragment() {
    private val viewModel: UpdateGoalsViewModel by viewModel()

    companion object {
        fun newInstance() = UpdateGoalsFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_update_goals, container, false)
    }

}