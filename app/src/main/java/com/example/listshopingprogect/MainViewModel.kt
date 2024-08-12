package com.example.listshopingprogect

import androidx.lifecycle.MutableLiveData
import com.example.listshopingprogect.data.ShopListRepositoryImpl
import com.example.listshopingprogect.domain.DeleteShopItemUseCase
import com.example.listshopingprogect.domain.EditShopItemUseCase
import com.example.listshopingprogect.domain.GetShopListUseCase
import com.example.listshopingprogect.domain.ShopItem

class MainViewModel {

    private val repository = ShopListRepositoryImpl

    private val shopList = MutableLiveData<List<ShopItem>>()

    private val getShopListUseCase = GetShopListUseCase(repository)
    val deleteShopListUseCase = DeleteShopItemUseCase(repository)
    val editShopListUseCase = EditShopItemUseCase(repository)

    fun getShopItem(){
        val element = getShopListUseCase.getShopList()
        shopList.value = element
    }

}