package com.example.listshopingprogect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listshopingprogect.domain.DeleteShopItemUseCase
import com.example.listshopingprogect.domain.EditShopItemUseCase
import com.example.listshopingprogect.domain.GetShopListUseCase
import com.example.listshopingprogect.domain.ShopItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

    getShopListUseCase: GetShopListUseCase,
    private val deleteShopListUseCase: DeleteShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,

    ) : ViewModel() {


    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(item: ShopItem) {
        viewModelScope.launch {
            deleteShopListUseCase.deleteShopItem(item)
        }
    }

    fun changeEnablesState(item: ShopItem) {
        viewModelScope.launch {
            val newItem = item.copy(enabled = !item.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}