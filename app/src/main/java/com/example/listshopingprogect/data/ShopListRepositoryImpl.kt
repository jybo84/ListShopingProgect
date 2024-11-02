package com.example.listshopingprogect.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.listshopingprogect.domain.ShopItem
import com.example.listshopingprogect.domain.ShopListRepository
import javax.inject.Inject

class ShopListRepositoryImpl @Inject constructor(

    private val shopListDao: ShopListDao,
    private val mappers: ShopItemMappers

) : ShopListRepository {

    override suspend fun addShopItem(shopItem: ShopItem) {
        (mappers.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mappers.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val element = shopListDao.getShopItem(shopItemId)
        return mappers.mapDbModelToEntity(element)
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListDao.getShopList().map { mappers.mapListDbModelToListEntity(it) }
    }
}
