package com.mwdziak.fitness_mobile_client.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.dto.MealRequest

class MealAdapter(private val meals: List<MealRequest>) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    inner class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ingredientsRecyclerView: RecyclerView = view.findViewById(R.id.ingredientsRecyclerView)
        private val nameTextView: TextView = view.findViewById(R.id.mealNameTextView)
        fun bind(meal: MealRequest) {
            nameTextView.text = meal.name

            val ingredientAdapter = IngredientAdapter(meal.ingredients)
            ingredientsRecyclerView.layoutManager = LinearLayoutManager(itemView.context) // Set the LayoutManager
            ingredientsRecyclerView.adapter = ingredientAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    override fun getItemCount(): Int = meals.size
}