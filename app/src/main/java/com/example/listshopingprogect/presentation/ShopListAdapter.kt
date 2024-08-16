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
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // 2. Объявляем переменную, что б потом мы могли в данном классе работать с интерфейсом.
    var onShopItemLongClick : OnShopItemLongClick? = null

    var onShopItemSimpleClick : OnShopItemSimpleClick? = null

    private var count = 0

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

        holder.tvName.text = item.name
        holder.tvCount.text = item.count.toString()

        if (item.enabled)
            holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
        else
            holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))

        // 3. Говорим системе что у адаптера будет логика при длительном нажатии и абстакцию ложим внутрь
        holder.itemView.setOnLongClickListener {
            onShopItemLongClick?.onLongClick(item)
            true
        }

        holder.itemView.setOnClickListener {
            onShopItemSimpleClick?.onSimpleClick(item)
            true
        }
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

    //1.  создал интерфейс с абстрактным методом. Реализовывать его буду в А/F.
    //    Адаптер  не может делать конкретную реализацию
    //    На одном экране при длительном нажатии меняется цвет элемента, а на другом удаляется элемент.
    //    Конктретную логику мы реализовываем в A/F
    interface OnShopItemLongClick {
        fun onLongClick(shopItem: ShopItem)
    }

    interface OnShopItemSimpleClick {
        fun onSimpleClick(shopItem: ShopItem)

    }
}