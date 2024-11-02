package com.example.listshopingprogect.di

import android.app.Application
import com.example.listshopingprogect.data.AppDatabase
import com.example.listshopingprogect.data.ShopListDao
import com.example.listshopingprogect.data.ShopListRepositoryImpl
import com.example.listshopingprogect.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface Module {

 @Binds
 fun bindShopListRepositoryImpl(impl: ShopListRepositoryImpl): ShopListRepository

    companion object{
        @Provides
        @Singleton
        fun provideShopListDao(application: Application):ShopListDao{
            return AppDatabase.getDb(application).shopListDao()
        }
    }
}