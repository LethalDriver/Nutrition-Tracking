package com.mwdziak.fitness_mobile_client.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import com.mwdziak.fitness_mobile_client.R

class AddMealRecycleViewAdapter(private val myDataset: Array<String>) :
    RecyclerView.Adapter<AddMealRecycleViewAdapter.MyViewHolder>() {

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val items = itemView.context.resources.getStringArray(R.array.items)
        val adapter = ArrayAdapter(itemView.context, R.layout.meal_autocomplete_item, items)
        val autoCompleteTextView = itemView.findViewById<AutoCompleteTextView>(R.id.foodKindTextView)

        init {
            autoCompleteTextView.setAdapter(adapter)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount() = myDataset.size
}