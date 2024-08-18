package com.example.listshopingprogect.presentation

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

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        var resultValidate = inputValidate(name, count)
        if (resultValidate) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun editShopItem(inputName: String?, inputCount: String) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        var resultValidate = inputValidate(name, count)
        if (resultValidate) {
            val shopItem = ShopItem(name, count, true)
            editShopItemUseCase.editShopItem(shopItem)
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
        val result = true
        if (name.isBlank())
            !result
        if (count <= 0)
            !result
        return result
    }
}