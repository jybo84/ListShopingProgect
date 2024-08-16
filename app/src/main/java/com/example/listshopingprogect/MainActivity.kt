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
import androidx.recyclerview.widget.RecyclerView
import com.example.listshopingprogect.domain.ShopItem
import com.example.listshopingprogect.presentation.ShopListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var myAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initAdapter()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this) {
            myAdapter.shopList = it
        }
    }

    private fun initAdapter() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        myAdapter = ShopListAdapter()
        rvShopList.apply {
            adapter = myAdapter
            recycledViewPool.setMaxRecycledViews(
                R.layout.item_shop_enabled,
                ShopListAdapter.MAX_POOL_ELEMENT
            )
            recycledViewPool.setMaxRecycledViews(
                R.layout.item_shop_disabled,
                ShopListAdapter.MAX_POOL_ELEMENT
            )
        }

        // 3. на мой адаптер вешаю конкретную реализацию
        myAdapter.onShopItemLongClick = {
            viewModel.changeEnablesState(it)
        }

        myAdapter.onShopItemSimpleClick = {
            Log.d("!!", it.toString())
        }

    }
}

