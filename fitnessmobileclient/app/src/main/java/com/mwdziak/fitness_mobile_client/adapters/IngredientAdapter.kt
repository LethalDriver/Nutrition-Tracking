package com.mwdziak.fitness_mobile_client.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.dto.IngredientRequest
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class IngredientAdapter(private val ingredients: List<IngredientRequest>) : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    inner class IngredientViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ingredientTextView: TextView = view.findViewById(R.id.ingredientNameTextView)
        private val calories: TextView = view.findViewById<TextView>(R.id.caloriesTextView)
        private val protein: TextView = view.findViewById<TextView>(R.id.proteinTextView)
        private val carbohydrates: TextView = view.findViewById<TextView>(R.id.carbsTextView)
        private val fat: TextView = view.findViewById<TextView>(R.id.fatTextView)
        private val weight: TextView = view.findViewById<TextView>(R.id.weightTextView)
        fun bind(ingredient: IngredientRequest) {
            ingredientTextView.text = "${ingredient.foodKind}, ${ingredient.description}"
            calories.text = ingredient.nutrients?.calories?.roundToInt().toString()
            protein.text = ingredient.nutrients?.protein?.roundToInt().toString()
            carbohydrates.text = ingredient.nutrients?.carbohydrates?.roundToInt().toString()
            fat.text = ingredient.nutrients?.fat?.roundToInt().toString()
            weight.text = ingredient.weight?.roundToInt().toString()
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