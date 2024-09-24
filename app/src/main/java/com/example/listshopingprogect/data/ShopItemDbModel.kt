package com.example.listshopingprogect.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_items")
data class ShopItemDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String,
    var count: Int,
    val enabled: Boolean,

    ) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
