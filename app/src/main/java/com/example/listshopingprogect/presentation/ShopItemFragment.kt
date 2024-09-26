package com.example.listshopingprogect.presentation


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.listshopingprogect.R
import com.google.android.material.textfield.TextInputLayout

class ShopItemFragment(

) : Fragment() {

    private var screenMode: String = MODE_UNKNOWN
    private var shopItemId: Int = UNDEFINED_ID

    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etCount: EditText
    private lateinit var buttonSave: Button

    private lateinit var viewModel: ShopItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews(view)
        parseParams()

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        observeViewModel()

        launchRightMode()

        addTextChangeListeners()
    }

    private fun observeViewModel() {
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it)
                getString(R.string.error_input_name)
            else {
                null
            }
            etName.error = message
        }

        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            val message = if (it)
                getString(R.string.error_input_count)
            else {
                null
            }
            etCount.error = message
        }

        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_ADD -> launchAddMode()
            MODE_EDIT -> launchEditMode()
        }
    }

    private fun addTextChangeListeners() {
        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun launchAddMode() {
        buttonSave.setOnClickListener {
            viewModel.addShopItem(etName.text?.toString(), etCount.text.toString())
        }
    }

    private fun launchEditMode() {
        viewModel.getShopItem(shopItemId)
        viewModel.shopItem.observe(viewLifecycleOwner) {
            etName.setText(it.name)
            etCount.setText(it.count.toString())
        }
        buttonSave.setOnClickListener {
            viewModel.editShopItem(etName.text?.toString(), etCount.text.toString())
        }
    }


    companion object {
        private const val SCREEN_MODE = "screen_mode"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val EXTRA_ITEM_ID = "extra_item_id"
        private const val UNDEFINED_ID = -1
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): ShopItemFragment {

            val bundle = Bundle()
            bundle.putString(SCREEN_MODE, MODE_ADD)
            val fragment = ShopItemFragment()
            fragment.arguments = bundle
            return fragment
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            val bundle = Bundle()
            bundle.putString(SCREEN_MODE, MODE_EDIT)
            bundle.putInt(EXTRA_ITEM_ID, shopItemId)
            val fragment = ShopItemFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun initViews(view: View) {

        tilName = view.findViewById(R.id.til_name)
        tilCount = view.findViewById(R.id.til_count)
        etName = view.findViewById(R.id.et_name)
        etCount = view.findViewById(R.id.et_count)
        buttonSave = view.findViewById(R.id.button_save)
    }

    private fun parseParams() {
        val bundle = requireArguments()
        if (!bundle.containsKey(SCREEN_MODE)) {
            throw RuntimeException("нет режима")
        }
        val mode = bundle.getString(SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("непонятный режим")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!bundle.containsKey(EXTRA_ITEM_ID)) {
                throw RuntimeException("не пришёл id")
            }
            shopItemId = bundle.getInt(EXTRA_ITEM_ID, UNDEFINED_ID)
        }
    }
}