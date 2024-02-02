package com.mwdziak.fitness_mobile_client.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.dto.DayRequest

class DayAdapter(private val days: List<DayRequest>) : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {

    inner class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mealsRecyclerView: RecyclerView = view.findViewById(R.id.mealsRecyclerView)
        private val dateTextView: TextView = view.findViewById(R.id.dayTextView)

        fun bind(day: DayRequest) {
            dateTextView.text = day.date
            val mealAdapter = MealAdapter(day.meals)
            mealsRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
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