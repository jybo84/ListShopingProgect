package com.example.listshopingprogect.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun addShopItem(shopItem: ShopItem){
        shopListRepository.addShopItem(shopItem)

    }
}