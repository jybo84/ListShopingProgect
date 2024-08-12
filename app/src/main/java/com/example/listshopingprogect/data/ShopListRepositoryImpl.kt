package com.example.listshopingprogect.data

import com.example.listshopingprogect.domain.ShopItem
import com.example.listshopingprogect.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

     private var autoIncrementId = 0

    init {
        for (el in 0..10) {
            val item = ShopItem("Name $el", el, true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        shopList.add(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId }?: throw RuntimeException("$shopItemId не найден")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }
}