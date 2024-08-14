package com.example.listshopingprogect.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.listshopingprogect.R
import com.example.listshopingprogect.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
    var count = 0

    class ShopItemViewHolder(element: View) : RecyclerView.ViewHolder(element) {
        val tvName = element.findViewById<TextView>(R.id.tv_name)
        val tvCount = element.findViewById<TextView>(R.id.tv_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_ENABLED -> R.layout.item_shop_enabled
            VIEW_DISABLE -> R.layout.item_shop_disabled
            else -> throw RuntimeException("НЕ известный layout")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        Log.d("!!!", "количество созданных view - ${++count}")
        return ShopItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = shopList[position]

        holder.itemView.setOnLongClickListener {
            true
        }

        holder.tvName.text = item.name
        holder.tvCount.text = item.count.toString()

        if (item.enabled)
            holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
        else
            holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.red))
    }

    override fun getItemViewType(position: Int): Int {
        val el = shopList[position]
        return if (el.enabled)
            1
        else
            2
    }

    companion object {
        const val VIEW_ENABLED = 1
        const val VIEW_DISABLE = 2

        const val MAX_POOL_ELEMENT = 25
    }
}