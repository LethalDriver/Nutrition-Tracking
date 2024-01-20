package com.mwdziak.fitness_mobile_client.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mwdziak.fitness_mobile_client.R

class IngredientAdapter(private val ingredients: List<Ingredient>) : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    inner class IngredientViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ingredientTextView: TextView = view.findViewById(R.id.ingredientTextView)
        val ingredientQuantityTextView: TextView = view.findViewById(R.id.ingredientQuantityTextView)

        fun bind(ingredient: Ingredient) {
            ingredientTextView.text = ingredient.name
            ingredientQuantityTextView.text = ingredient.quantity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount(): Int = ingredients.size
}