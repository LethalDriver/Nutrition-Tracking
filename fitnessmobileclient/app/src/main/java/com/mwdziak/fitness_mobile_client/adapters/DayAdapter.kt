package com.mwdziak.fitness_mobile_client.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwdziak.fitness_mobile_client.R

class DayAdapter(private val days: List<Day>) : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {

    inner class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mealsRecyclerView: RecyclerView = view.findViewById(R.id.mealsRecyclerView)

        fun bind(day: Day) {
            // TODO: Bind day data to views

            val mealAdapter = MealAdapter(day.meals)
            mealsRecyclerView.adapter = mealAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(days[position])
    }

    override fun getItemCount(): Int = days.size
}