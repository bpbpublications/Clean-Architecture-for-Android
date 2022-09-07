package com.favedish.app.di

import com.favedish.data.addnewdish.mapper.NewDishDomainToDishRequestDataMapper
import com.favedish.data.addnewdish.repository.AddNewDishLiveRepository
import com.favedish.data.dish.datasource.DishDataSource
import com.favedish.domain.addnewdish.repository.AddNewDishRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NewDishDataModule {
    @Provides
    fun providesNewDishDomainToDishRequestDataMapper() = NewDishDomainToDishRequestDataMapper()

    @Provides
    fun providesAddNewDishRepository(
        dishDataSource: DishDataSource,
        newDishDomainToDishRequestDataMapper: NewDishDomainToDishRequestDataMapper
    ): AddNewDishRepository =
        AddNewDishLiveRepository(dishDataSource, newDishDomainToDishRequestDataMapper)
}
