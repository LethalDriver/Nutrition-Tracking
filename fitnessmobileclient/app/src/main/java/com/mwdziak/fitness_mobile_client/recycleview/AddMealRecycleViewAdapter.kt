package com.mwdziak.fitness_mobile_client.recycleview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.domain.Ingredient

class AddMealRecycleViewAdapter(private val ingredients: MutableList<Ingredient>,
                                private val foodCategories: Array<String>) :
    RecyclerView.Adapter<IngredientViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient_item, parent, false)

        return IngredientViewHolder(view, foodCategories, ::modifyIngredient, ::deleteIngredient)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount() = ingredients.size

    private fun modifyIngredient(position: Int, ingredient: Ingredient) {
        ingredients[position] = ingredient
        notifyItemChanged(position)

        Log.d("AddMealRecycleViewAdapter", "modifyIngredient: $ingredient")
    }

    private fun deleteIngredient(position: Int) {
        ingredients.removeAt(position)
        notifyItemRemoved(position)
    }
}