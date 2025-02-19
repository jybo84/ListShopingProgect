package com.example.listshopingprogect.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listshopingprogect.domain.AddShopItemUseCase
import com.example.listshopingprogect.domain.EditShopItemUseCase
import com.example.listshopingprogect.domain.GetShopItemUseCase
import com.example.listshopingprogect.domain.ShopItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopItemViewModel @Inject constructor(
    private val getShopItemUseCase: GetShopItemUseCase,
    private val addShopItemUseCase: AddShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
    ) : ViewModel() {

    private var _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean> = _errorInputName

    private var _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean> = _errorInputName

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem> = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit> = _shouldCloseScreen

    fun getShopItem(shopItemId: Int) {
        viewModelScope.launch {
            val item = getShopItemUseCase.getShopItem(shopItemId)
            _shopItem.value = item
        }
    }

    fun addShopItem(inputName: String?, inputCount: String) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val resultValidate = inputValidate(name, count)
        if (resultValidate) {
            viewModelScope.launch {
                val shopItem = ShopItem(name, count, true)
                addShopItemUseCase.addShopItem(shopItem)
            }
            finishWork()
        }
    }

    fun editShopItem(inputName: String?, inputCount: String) {
        val editName = parseName(inputName)
        val editCount = parseCount(inputCount)
        val resultValidate = inputValidate(editName, editCount)
        if (resultValidate) {
            _shopItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(name = editName, count = editCount)
                    editShopItemUseCase.editShopItem(item)
                }
                finishWork()
            }
        }
    }

    private fun parseName(inputCount: String?): String {
        return inputCount?.trim() ?: " "
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun inputValidate(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
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