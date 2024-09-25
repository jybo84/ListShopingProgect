package com.example.listshopingprogect.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Transaction
import com.example.listshopingprogect.domain.ShopItem
import com.example.listshopingprogect.domain.ShopListRepository
import kotlin.random.Random

class ShopListRepositoryImpl(application: Application) : ShopListRepository {

    private val database = AppDatabase.getDb(application).shopListDao()
    private val mappers = ShopItemMappers()

    override fun addShopItem(shopItem: ShopItem) {
        database.addShopItem(mappers.mapEntityToDbModel(shopItem))
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        database.deleteShopItem(shopItem.id)
    }

    override fun editShopItem(shopItem: ShopItem) {
        database.addShopItem(mappers.mapEntityToDbModel(shopItem))
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        val element = database.getShopItem(shopItemId)
        return mappers.mapDbModelToEntity(element)
    }

    override fun getShopList(): LiveData<List<ShopItem>> = MediatorLiveData<List<ShopItem>>().apply {
        addSource(database.getShopList()){
            value = mappers.mapListDbModelToListEntity(it)
    }
    }
}
