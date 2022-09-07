package com.favedish.domain.addnewdish.repository

import com.favedish.domain.addnewdish.model.NewDishDomainModel

interface AddNewDishRepository {
    fun addNewDish(newDish: NewDishDomainModel): String
}
