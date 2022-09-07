package com.favedish.datasource.dish.datasource

import com.favedish.data.dish.datasource.DishDataSource
import com.favedish.data.dish.model.DishRequestDataModel
import com.favedish.data.dish.model.DishResponseDataModel
import com.favedish.datasource.dish.RandomUuidProvider
import com.favedish.datasource.dish.mapper.DishMemoryToResponseDataMapper
import com.favedish.datasource.dish.mapper.DishRequestDataToMemoryResolver
import com.favedish.datasource.dish.mapper.RestaurantMemoryToDataMapper
import com.favedish.datasource.dish.model.DishMemoryModel
import com.favedish.datasource.dish.model.RestaurantMemoryModel

class DishLiveDataSource(
    private val restaurantStore: MutableMap<String, RestaurantMemoryModel>,
    private val dishStore: MutableMap<String, DishMemoryModel>,
    private val restaurantMemoryToDataMapper: RestaurantMemoryToDataMapper,
    private val dishRequestDataToMemoryResolver: DishRequestDataToMemoryResolver,
    private val dishMemoryToResponseDataMapper: DishMemoryToResponseDataMapper,
    private val randomUuidProvider: RandomUuidProvider
) : DishDataSource {
    override fun addNewDish(newDish: DishRequestDataModel): String {
        val memoryRestaurant = requireNotNull(
            restaurantStore[newDish.restaurantId]
        ) { "Restaurant not found for ID: ${newDish.restaurantId}" }
        val dataRestaurant = restaurantMemoryToDataMapper
            .toData(memoryRestaurant)
        val dishId = randomUuidProvider.randomUuid()
        val memoryDish = dishRequestDataToMemoryResolver
            .toMemory(newDish, dishId, dataRestaurant)
        dishStore[dishId] = memoryDish
        return dishId
    }

    override fun dishDetails(dishId: String): DishResponseDataModel {
        val dish = requireNotNull(
            dishStore[dishId]
        ) { "Dish $dishId not found" }
        return dishMemoryToResponseDataMapper.toData(dish)
    }
}
