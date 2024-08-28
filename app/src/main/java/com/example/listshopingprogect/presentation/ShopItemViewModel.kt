package com.example.listshopingprogect.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listshopingprogect.data.ShopListRepositoryImpl
import com.example.listshopingprogect.domain.AddShopItemUseCase
import com.example.listshopingprogect.domain.EditShopItemUseCase
import com.example.listshopingprogect.domain.GetShopItemUseCase
import com.example.listshopingprogect.domain.ShopItem

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    val getShopItemUseCase = GetShopItemUseCase(repository)
    val addShopItemUseCase = AddShopItemUseCase(repository)
    val editShopItemUseCase = EditShopItemUseCase(repository)

    private var _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean> = _errorInputName

    private var _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean> = _errorInputName

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem> = _shopItem

    private val _shouldCloseScreen = MutableLiveData(Unit)
    val shouldCloseScreen: LiveData<Unit> = _shouldCloseScreen

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
        _shopItem.value = item
    }

    fun addShopItem(inputName: String?, inputCount: String) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val resultValidate = inputValidate(name, count)
        if (resultValidate) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
            finishWork()
        }
    }

    fun editShopItem(inputName: String?, inputCount: String) {
        val editName = parseName(inputName)
        val editCount = parseCount(inputCount)
        val resultValidate = inputValidate(editName, editCount)
        if (resultValidate) {
            _shopItem.value?.let {
                val item = it.copy(name = editName, count = editCount)
                editShopItemUseCase.editShopItem(item)
                finishWork()
            }
        }
    }

    private fun parseName(inputCount: String?): String {
        return inputCount?.trim() ?: " "
    }

    private fun parseCount(inputCount: String?): Int {
        return inputCount?.trim()?.toInt() ?: 0
    }

    private fun inputValidate(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0){
            _errorInputCount.value = true
        result = false
            }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}