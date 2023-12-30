package com.mwdziak.fitness_mobile_client.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mwdziak.fitness_mobile_client.AddMealViewModel
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.adapter.AddMealRecycleViewAdapter
import com.mwdziak.fitness_mobile_client.databinding.FragmentAddMealBinding

class AddMealFragment : Fragment() {

    companion object {
        fun newInstance() = AddMealFragment()
    }

    private lateinit var viewModel: AddMealViewModel
    private var _binding: FragmentAddMealBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.mealRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val myDataset = arrayOf("Item 1", "Item 2", "Item 3")
        recyclerView.adapter = AddMealRecycleViewAdapter(myDataset)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}