package com.example.customcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var op: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActionState()
        gv_operations.adapter = OperationAdapter(applicationContext)

        initEditTextState()
    }

    private fun initActionState() {
        btn_add.setOnClickListener(this)
        btn_sub.setOnClickListener(this)
        btn_mul.setOnClickListener(this)
        btn_div.setOnClickListener(this)
        btn_equal.setOnClickListener(this)
        btn_undo.setOnClickListener(this)
        btn_redo.setOnClickListener(this)

        btn_undo.isClickable = false
        btn_redo.isClickable = false
        btn_equal.isClickable = false
    }

    private fun initEditTextState() {
        et_operand.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                text?.let {
                    btn_equal.isClickable = text.isNotEmpty() && op != null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    override fun onClick(view: View?) {

        when (view?.id) {

            btn_add.id -> {
                op = btn_add.text.toString()
            }
            btn_sub.id -> {

                op = btn_sub.text.toString()
            }
            btn_mul.id -> {

                op = btn_mul.text.toString()
            }
            btn_div.id -> {
                op = btn_div.text.toString()
            }
            btn_equal.id -> {
                op ?:
                Log.d("equal", op.toString())
            }
        }

        Log.d("op", op.toString())
    }
}