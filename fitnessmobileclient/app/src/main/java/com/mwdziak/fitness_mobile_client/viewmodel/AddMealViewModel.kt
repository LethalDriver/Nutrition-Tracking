package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwdziak.fitness_mobile_client.dto.FoodGetRequest
import com.mwdziak.fitness_mobile_client.dto.IngredientRequest
import com.mwdziak.fitness_mobile_client.dto.MealRequest
import com.mwdziak.fitness_mobile_client.service.HttpService
import com.mwdziak.fitness_mobile_client.service.Validator
import kotlinx.coroutines.launch

class AddMealViewModel(private val httpService: HttpService, private val validator: Validator) : ViewModel() {
    private val forms = mutableListOf<IngredientFormViewModel>()
    private val foodKinds = mutableListOf<String>()
    private val mealName = MutableLiveData<String>("")

    fun addIngredient(formViewModel: IngredientFormViewModel) {
        forms.add(formViewModel)
    }
    fun removeIngredient(formViewModel: IngredientFormViewModel) {
        forms.remove(formViewModel)
    }

    private suspend fun fetchFoodKinds() {
        val kinds = httpService.getFoodKinds()
        foodKinds.clear()
        foodKinds.addAll(kinds)
    }

    private fun mapFoodDtoToKinds(foods: List<FoodGetRequest>): List<String> {
        return foods.map { it.foodKind }
    }

    fun getFoodCategories(): List<String> {
        return foodKinds
    }
    fun checkIfAllFieldsValid(): Boolean {
        return forms.all { it.getIsAllFieldsValid() } && isMealNameValid()
    }

    fun updateMealName(newMealName: String) {
        mealName.value = newMealName
    }

    private fun isMealNameValid(): Boolean {
        return validator.isNotBlank(mealName.value ?: "")
    }

    fun MapFormsToFoodPostRequest(): List<IngredientRequest> {
        return forms.map { it.mapFormToFoodPostRequest() }
    }

    fun mapMealToPostRequest(): MealRequest {
        return MealRequest(
            name = mealName.value ?: "",
            ingredients = MapFormsToFoodPostRequest()
        )
    }

    suspend fun postMeal() {
        val meal = mapMealToPostRequest()
        httpService.postMeal(meal)
    }

    suspend fun updateFormsWithFoodKinds() {
        val job = viewModelScope.launch {
            fetchFoodKinds()
        }
        job.join()
        forms.forEach { it.updateFoodKinds(foodKinds) }
    }


}