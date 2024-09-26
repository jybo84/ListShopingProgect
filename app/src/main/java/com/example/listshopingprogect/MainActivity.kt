package com.example.listshopingprogect

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.listshopingprogect.presentation.ShopItemActivity
import com.example.listshopingprogect.presentation.ShopItemFragment
import com.example.listshopingprogect.presentation.ShopListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var myAdapter: ShopListAdapter
    private var fragmentContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        fragmentContainer = findViewById(R.id.fragment_container)
        initAdapter()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this) {
            myAdapter.shopListAdapter = it
        }

        val buttonAdd = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        buttonAdd.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceAddItem())
            }
        }
    }

    private fun isOnePaneMode(): Boolean {
        return fragmentContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initAdapter() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        myAdapter = ShopListAdapter()

        setupAdapter(rvShopList)

        setupLongClick()

        setupCLick()

        setupSwipe(rvShopList)
    }

    private fun setupSwipe(rvShopList: RecyclerView?) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = myAdapter.shopListAdapter[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setupAdapter(rvShopList: RecyclerView?) {
        rvShopList?.apply {
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
    }

    private fun setupLongClick() {
        myAdapter.onShopItemLongClick = {
            viewModel.changeEnablesState(it)
        }
    }

    private fun setupCLick() {
        myAdapter.onShopItemSimpleClick = {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceEditItem(it.id))
            }
        }
    }
}

