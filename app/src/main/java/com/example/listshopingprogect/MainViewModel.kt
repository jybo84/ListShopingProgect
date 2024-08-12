package com.example.listshopingprogect

import com.example.listshopingprogect.data.ShopListRepositoryImpl
import com.example.listshopingprogect.domain.DeleteShopItemUseCase
import com.example.listshopingprogect.domain.EditShopItemUseCase
import com.example.listshopingprogect.domain.GetShopListUseCase

class MainViewModel {

    private val repository = ShopListRepositoryImpl

    val getShopListUseCase = GetShopListUseCase(repository)
    val deleteShopListUseCase = DeleteShopItemUseCase(repository)
    val editShopListUseCase = EditShopItemUseCase(repository)

}