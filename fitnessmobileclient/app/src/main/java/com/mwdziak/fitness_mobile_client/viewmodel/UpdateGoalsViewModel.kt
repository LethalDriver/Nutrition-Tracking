package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.service.Validator
import io.ktor.client.HttpClient

class UpdateGoalsViewModel(val httpClient: HttpClient, val validator: Validator) : ViewModel() {



}