package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.dto.FoodGetRequest
import com.mwdziak.fitness_mobile_client.dto.IngredientPostRequest
import com.mwdziak.fitness_mobile_client.dto.MealPostRequest
import com.mwdziak.fitness_mobile_client.service.HttpService
import com.mwdziak.fitness_mobile_client.service.Validator

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

    suspend fun fetchFoodKinds() {
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

    fun MapFormsToFoodPostRequest(): List<IngredientPostRequest> {
        return forms.map { it.mapFormToFoodPostRequest() }
    }

    fun mapMealToPostRequest(): MealPostRequest {
        return MealPostRequest(
            mealName = mealName.value ?: "",
            foods = MapFormsToFoodPostRequest()
        )
    }
}