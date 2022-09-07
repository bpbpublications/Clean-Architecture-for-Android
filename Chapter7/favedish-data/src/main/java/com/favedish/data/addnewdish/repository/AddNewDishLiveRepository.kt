package com.favedish.data.addnewdish.repository

import com.favedish.data.addnewdish.mapper.NewDishDomainToDishRequestDataMapper
import com.favedish.data.dish.datasource.DishDataSource
import com.favedish.domain.addnewdish.model.NewDishDomainModel
import com.favedish.domain.addnewdish.repository.AddNewDishRepository

class AddNewDishLiveRepository(
    private val dishDataSource: DishDataSource,
    private val newDishDomainToDishRequestDataMapper: NewDishDomainToDishRequestDataMapper
) : AddNewDishRepository {
    override fun addNewDish(newDish: NewDishDomainModel): String {
        val dataDishRequest = newDishDomainToDishRequestDataMapper
            .toData(newDish)
        return dishDataSource.addNewDish(dataDishRequest)
    }
}
