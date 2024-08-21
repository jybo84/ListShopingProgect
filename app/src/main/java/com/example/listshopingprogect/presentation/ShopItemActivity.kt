package com.example.listshopingprogect.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listshopingprogect.R
import com.google.android.material.textfield.TextInputLayout

class ShopItemActivity : AppCompatActivity() {

//    private lateinit var tilName: TextInputLayout
//    private lateinit var tilCount: TextInputLayout
//    private lateinit var etName: EditText
//    private lateinit var etCount: EditText
//    private lateinit var buttonSave: Button
//
//    private lateinit var viewModel: ShopItemViewModel
//
//    private var screenMode = MODE_UNKNOWN
//    private var shopItemId = UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        enableEdgeToEdge()
//        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//        initViews()
//        parseItem()
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//        launchRightMode()
//
//        addTextChangeListeners()
//        observeViewModel()
    }

//    private fun observeViewModel() {
//        viewModel.errorInputName.observe(this) {
//            val message = if (it)
//                getString(R.string.error_input_name)
//            else {
//                null
//            }
//            etName.error = message
//        }
//
//        viewModel.errorInputCount.observe(this) {
//            val message = if (it)
//                getString(R.string.error_input_count)
//            else {
//                null
//            }
//            etCount.error = message
//        }
//
//        viewModel.shouldCloseScreen.observe(this) {
//            finish()
//        }
//    }
//
//    private fun launchRightMode() {
//        when (screenMode) {
//            MODE_ADD -> launchAddMode()
//            MODE_EDIT -> launchEditMode()
//        }
//    }
//
//    private fun addTextChangeListeners() {
//        etName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//
//        })
//
//        etCount.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputCount()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//
//        })
//    }
//
//    private fun launchAddMode() {
//        buttonSave.setOnClickListener {
//            viewModel.addShopItem(etName.text?.toString(), etCount.text.toString())
//        }
//
//    }
//
//    private fun launchEditMode() {
//        viewModel.getShopItem(shopItemId)
//        viewModel.shopItem.observe(this) {
//            etName.setText(it.name)
//            etCount.setText(it.count.toString())
//        }
//        buttonSave.setOnClickListener {
//            viewModel.editShopItem(etName.text?.toString(), etCount.text.toString())
//        }
//    }
//
//
//    companion object {
//        private const val SCREEN_MODE = "screen_mode"
//        private const val MODE_ADD = "mode_add"
//        private const val MODE_EDIT = "mode_edit"
//        private const val EXTRA_ITEM_ID = "extra_item_id"
//        private const val UNDEFINED_ID = -1
//        private const val MODE_UNKNOWN = ""
//
//        fun newIntentAddItem(context: Context): Intent {
//            val intent = Intent(context, ShopItemActivity::class.java)
//            intent.putExtra(SCREEN_MODE, MODE_ADD)
//            return intent
//        }
//
//        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
//            val intent = Intent(context, ShopItemActivity::class.java)
//            intent.putExtra(SCREEN_MODE, MODE_EDIT)
//            intent.putExtra(EXTRA_ITEM_ID, shopItemId)
//            return intent
//        }
//    }
//
//    private fun initViews() {
//
//        tilName = findViewById(R.id.til_name)
//        tilCount = findViewById(R.id.til_count)
//        etName = findViewById(R.id.et_name)
//        etCount = findViewById(R.id.et_count)
//        buttonSave = findViewById(R.id.button_save)
//    }
//
//    private fun parseItem() {
//        if (!intent.hasExtra(SCREEN_MODE))
//            throw RuntimeException("нет режима")
//        val mode = intent.getStringExtra(SCREEN_MODE)
//        if (mode != MODE_EDIT && mode != MODE_ADD) {
//            throw RuntimeException("режим не соответствует")
//        }
//        screenMode = mode
//        if (screenMode == MODE_EDIT)
//            if (!intent.hasExtra(EXTRA_ITEM_ID)) {
//                throw RuntimeException("Не пришло id")
//            }
//        shopItemId = intent.getIntExtra(EXTRA_ITEM_ID, -1)
//
//    }
}