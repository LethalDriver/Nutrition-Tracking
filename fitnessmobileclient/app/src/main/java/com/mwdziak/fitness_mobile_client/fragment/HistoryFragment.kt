package com.mwdziak.fitness_mobile_client.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.adapters.DayAdapter
import com.mwdziak.fitness_mobile_client.databinding.FragmentHistoryBinding
import com.mwdziak.fitness_mobile_client.databinding.FragmentMainDashboardBinding
import com.mwdziak.fitness_mobile_client.viewmodel.HistoryViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private val viewModel: HistoryViewModel by viewModel()
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val topAppBar = activity?.findViewById<MaterialToolbar>(R.id.topAppBar)
        topAppBar?.title = "History"
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHistoryBinding.bind(view)
        lifecycleScope.launch {
            viewModel.fetchDays()
            val adapter = DayAdapter(viewModel.getDays())
            binding.dayRecyclerView.layoutManager = LinearLayoutManager(context) // Set the LayoutManager
            binding.dayRecyclerView.adapter = adapter
        }
        Log.w("HistoryFragment", "onViewCreated: ${viewModel.getDays()}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}