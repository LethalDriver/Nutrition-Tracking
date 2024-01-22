package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.dto.DayRequest
import com.mwdziak.fitness_mobile_client.service.HttpService

class HistoryViewModel(private val httpService: HttpService): ViewModel(){
    private val days: MutableList<DayRequest> = mutableListOf();


    suspend fun fetchDays() {
        val fetchedDays = httpService.getUserDays();
        days.clear();
        days.addAll(fetchedDays);
    }

    fun getDays(): List<DayRequest> {
        return days;
    }
}