package com.mwdziak.fitness_mobile_client.recycleview

import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.domain.Ingredient

class IngredientViewHolder(view : View, foodCategories: Array<String>,
                           private val modifyIngredient: (Int, Ingredient) -> Unit,
                           private val deleteIngredient: (Int) -> Unit)
    : RecyclerView.ViewHolder(view) {

    private val foodCategoriesAdapter = ArrayAdapter(itemView.context, R.layout.meal_autocomplete_item, foodCategories)
    private val unitsAdapter = ArrayAdapter(itemView.context, R.layout.meal_autocomplete_item, arrayOf("g", "lb"))
    private val foodKindTextView = itemView.findViewById<AutoCompleteTextView>(R.id.foodKindTextView)
    private val foodDescriptionTextView = itemView.findViewById<AutoCompleteTextView>(R.id.foodDescriptionTextView)
    private val weightTextView = itemView.findViewById<TextInputEditText>(R.id.weightEditText)
    private val unitTextView = itemView.findViewById<AutoCompleteTextView>(R.id.unitAutoCompleteTextView)
    fun bind(ingredient: Ingredient) {
        foodKindTextView.setText(ingredient.name)
        foodDescriptionTextView.setText(ingredient.description)
        weightTextView.setText(ingredient.weight.toString())
        unitTextView.setText(ingredient.unit)
    }

    init {
        foodKindTextView.setAdapter(foodCategoriesAdapter)
        unitTextView.setAdapter(unitsAdapter)

        foodKindTextView.setOnItemClickListener { _, _, position, _ ->
            modifyIngredient(adapterPosition, Ingredient(name = foodCategories[position]))
            foodKindTextView.clearFocus()
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