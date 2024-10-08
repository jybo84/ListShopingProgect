package com.example.listshopingprogect.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.room.Transaction
import com.example.listshopingprogect.domain.ShopItem
import com.example.listshopingprogect.domain.ShopListRepository
import kotlin.random.Random

class ShopListRepositoryImpl(application: Application) : ShopListRepository {

    private val database = AppDatabase.getDb(application).shopListDao()
    private val mappers = ShopItemMappers()

    override suspend fun addShopItem(shopItem: ShopItem) {
        database.addShopItem(mappers.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        database.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        database.addShopItem(mappers.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val element = database.getShopItem(shopItemId)
        return mappers.mapDbModelToEntity(element)
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return database.getShopList().map { mappers.mapListDbModelToListEntity(it) }
    }
}
