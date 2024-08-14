package com.example.listshopingprogect.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.listshopingprogect.R
import com.example.listshopingprogect.domain.ShopItem

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()

    class ShopItemViewHolder(element: View) : RecyclerView.ViewHolder(element) {
        val tvName = element.findViewById<TextView>(R.id.tv_name)
        val tvCount = element.findViewById<TextView>(R.id.tv_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_shop_enabled, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = shopList[position]
        val status = if (item.enabled)
            "Активный"
        else
            "Не активный"
        holder.itemView.setOnLongClickListener {
            true
        }

        if (item.enabled) {
            holder.tvName.text = status
            holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.green))
            holder.tvCount.text = item.count.toString()
        } else {
            holder.tvName.text = status
            holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.black))
            holder.tvCount.text = item.count.toString()
}
    }
}