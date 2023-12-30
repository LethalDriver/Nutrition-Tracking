package com.mwdziak.fitness_mobile_client.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.mwdziak.fitness_mobile_client.viewmodel.AddMealViewModel
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.databinding.FragmentAddMealBinding
import com.mwdziak.fitness_mobile_client.domain.Ingredient
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddMealFragment : Fragment() {
    private val viewModel: AddMealViewModel by viewModel()
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
        addForm()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addForm() {
        val newForm = LayoutInflater.from(requireContext()).inflate(R.layout.ingredient_item, binding.formContainer, false)
        binding.formContainer.addView(newForm)
        viewModel.ingredients.add(Ingredient())
        initForm(newForm, viewModel.ingredients.size - 1)
    }

    private fun removeForm(form: View) {
        binding.formContainer.removeView(form)
    }

    companion object {
        fun newInstance() = AddMealFragment()
    }

    private fun initForm(view: View, position: Int){
        val foodCategories = resources.getStringArray(R.array.food_categories)
        val foodCategoriesAdapter = ArrayAdapter(view.context, R.layout.meal_autocomplete_item, foodCategories)
        val unitsAdapter = ArrayAdapter(view.context, R.layout.meal_autocomplete_item, arrayOf("g", "lb"))
        val foodKindTextView = view.findViewById<AutoCompleteTextView>(R.id.foodKindTextView)
        val foodDescriptionTextView = view.findViewById<AutoCompleteTextView>(R.id.foodDescriptionTextView)
        val weightTextView = view.findViewById<TextInputEditText>(R.id.weightEditText)
        val unitTextView = view.findViewById<AutoCompleteTextView>(R.id.unitAutoCompleteTextView)
        foodKindTextView.setAdapter(foodCategoriesAdapter)
        unitTextView.setAdapter(unitsAdapter)

        foodKindTextView.setOnItemClickListener { _, _, dropdownPosition, _ ->
            viewModel.ingredients[position].name = foodCategories[dropdownPosition]
            foodKindTextView.clearFocus()
            Log.w("AddMealFragment", viewModel.ingredients.toString())
        }

        foodKindTextView.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val enteredText = (v as AutoCompleteTextView).text.toString()
                if (!foodCategories.contains(enteredText)) {
                    v.setText("")
                }
            }
        }

    }
}