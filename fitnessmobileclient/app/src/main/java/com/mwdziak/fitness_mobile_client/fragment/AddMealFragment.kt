package com.mwdziak.fitness_mobile_client.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.mwdziak.fitness_mobile_client.viewmodel.AddMealViewModel
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.databinding.FragmentAddMealBinding
import com.mwdziak.fitness_mobile_client.viewmodel.IngredientFormViewModel
import kotlinx.coroutines.launch
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
        binding.addButton.setOnClickListener {
            addForm()
        }
        lifecycleScope.launch {
            val job = viewModel.viewModelScope.launch {
                viewModel.fetchFoodKinds()
            }
            job.join()
            viewModel.updateFormsWithFoodKinds()
        }
        binding.discardButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.saveMealButton.setOnClickListener {
            if (viewModel.checkIfAllFieldsValid()) {
                showSnackBar("Meal saved", false)
                findNavController().navigate(R.id.action_addMealFragment_to_mainDashboardFragment)
            } else {
                showSnackBar("Please fill all fields", true)
            }
        }
        binding.mealNameEditText.addTextChangedListener { text ->
            viewModel.updateMealName(text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addForm() {
        val newForm = LayoutInflater.from(requireContext()).inflate(R.layout.ingredient_item, binding.formContainer, false)
        binding.formContainer.addView(newForm)
        initForm(newForm)
    }

    private fun removeForm(form: View) {
        binding.formContainer.removeView(form)
    }

    companion object {
        fun newInstance() = AddMealFragment()
    }

    private fun initForm(view: View){
        val formViewModel: IngredientFormViewModel by viewModel()
        viewModel.addIngredient(formViewModel)

        formViewModel.setFoodKinds(viewModel.getFoodCategories())

        val foodKindsAdapter = ArrayAdapter(view.context, R.layout.meal_autocomplete_item, viewModel.getFoodCategories())

        val foodKindTextView = view.findViewById<AutoCompleteTextView>(R.id.foodKindTextView)

        val foodDescriptionTextView = view.findViewById<AutoCompleteTextView>(R.id.foodDescriptionTextView)
        val foodDescriptionAdapter = ArrayAdapter(view.context, R.layout.meal_autocomplete_item, formViewModel.getFoodDescriptions())

        val weightTextView = view.findViewById<TextInputEditText>(R.id.weightEditText)

        val unitTextView = view.findViewById<AutoCompleteTextView>(R.id.unitAutoCompleteTextView)
        val unitsAdapter = ArrayAdapter(view.context, R.layout.meal_autocomplete_item, formViewModel.getWeightUnits())


        val deleteButton = view.findViewById<View>(R.id.deleteButton)

        foodKindTextView.setAdapter(foodKindsAdapter)
        foodDescriptionTextView.setAdapter(foodDescriptionAdapter)
        unitTextView.setAdapter(unitsAdapter)

        foodKindTextView.setOnItemClickListener { _, _, dropdownPosition, _ ->
            formViewModel.viewModelScope.launch {
                formViewModel.updatePickedFoodKind(viewModel.getFoodCategories()[dropdownPosition])
                formViewModel.fetchFoodsByKind()
            }
            foodDescriptionAdapter.notifyDataSetChanged()
            foodKindTextView.clearFocus()
            Log.w("Form data: ", formViewModel.toString())
        }

        foodKindTextView.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val enteredText = (v as AutoCompleteTextView).text.toString()
                if (!viewModel.getFoodCategories().contains(enteredText)) {
                    v.setText("")
                }
            }
        }

        foodDescriptionTextView.setOnItemClickListener { _, _, dropdownPosition, _ ->
            formViewModel.updatePickedFoodDescription(dropdownPosition)
            foodDescriptionTextView.clearFocus()
        }

        unitTextView.setOnItemClickListener { _, _, dropdownPosition, _ ->
            formViewModel.updatePickedWeightUnit(dropdownPosition)
            unitTextView.clearFocus()
            Log.w("Form data: ", formViewModel.toString())
        }

        weightTextView.addTextChangedListener { text ->
            formViewModel.updatePickedFoodWeight(text.toString().toDouble())
        }

        deleteButton.setOnClickListener {
            removeForm(view)
            viewModel.removeIngredient(formViewModel)
        }
    }

    fun showSnackBar(message: String, error: Boolean) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
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
}