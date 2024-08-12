package com.example.listshopingprogect

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listshopingprogect.data.ShopListRepositoryImpl
import com.example.listshopingprogect.domain.DeleteShopItemUseCase
import com.example.listshopingprogect.domain.EditShopItemUseCase
import com.example.listshopingprogect.domain.GetShopListUseCase
import com.example.listshopingprogect.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

     val shopList = MutableLiveData<List<ShopItem>>()

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopListUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    fun getShopList(){
        val element = getShopListUseCase.getShopList()
        shopList.value = element
    }

    fun deleteShopItem(item: ShopItem){
        deleteShopListUseCase.deleteShopItem(item)
        getShopList()
    }

    fun changeEnablesState(item: ShopItem){
       val newItem = item.copy(enabled = !item.enabled)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }
}