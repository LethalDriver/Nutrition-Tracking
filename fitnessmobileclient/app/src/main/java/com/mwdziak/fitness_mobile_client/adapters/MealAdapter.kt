package com.mwdziak.fitness_mobile_client.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwdziak.fitness_mobile_client.R

class MealAdapter(private val meals: List<Meal>) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    inner class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ingredientsRecyclerView: RecyclerView = view.findViewById(R.id.ingredientsRecyclerView)

        fun bind(meal: Meal) {
            // TODO: Bind meal data to views

            val ingredientAdapter = IngredientAdapter(meal.ingredients)
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