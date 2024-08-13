package com.example.listshopingprogect

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.listshopingprogect.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var llShopList: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        llShopList = findViewById(R.id.ll_shop_list)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this) {
            showList(it)
        }
    }

    private fun showList(list: List<ShopItem>) {
        llShopList.removeAllViews()
        for (el in list) {
            val layout = if (el.enabled)
                R.layout.item_shop_enabled
            else
                R.layout.item_shop_disabled
            val view = LayoutInflater.from(this).inflate(layout, llShopList, false)

            val tvName = view.findViewById<TextView>(R.id.tv_name)
            tvName.text = el.name

            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvCount.text = el.count.toString()

            view.setOnLongClickListener {
                viewModel.changeEnablesState(el)
                true
            }

            llShopList.addView(view)
        }
    }
}
