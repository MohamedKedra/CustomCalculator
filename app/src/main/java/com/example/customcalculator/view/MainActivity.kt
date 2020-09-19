package com.example.customcalculator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.customcalculator.R
import com.example.customcalculator.models.Calculator
import com.example.customcalculator.presenter.CalculatorContract
import com.example.customcalculator.presenter.CalculatorPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener ,CalculatorContract.View{

    private lateinit var presenter: CalculatorPresenter
    private lateinit var adapter: OperationAdapter
    private var op: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActionState()

        initEditTextState()
    }

    private fun initActionState() {

        tv_result.text = resources.getText(R.string.result).toString().plus(0)

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

        adapter = OperationAdapter(applicationContext)

        presenter = CalculatorPresenter(this)
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
                presenter.setOperator(op.toString())
            }
            btn_sub.id -> {
                op = btn_sub.text.toString()
                presenter.setOperator(op.toString())
            }
            btn_mul.id -> {
                op = btn_mul.text.toString()
                presenter.setOperator(op.toString())
            }
            btn_div.id -> {
                op = btn_div.text.toString()
                presenter.setOperator(op.toString())
            }
            btn_equal.id -> {
                presenter.completeCalculation(et_operand.text.toString())
            }
            btn_undo.id -> {

                if (presenter.getList().isNotEmpty())
                      presenter.undoOperation()
            }
            btn_redo.id -> {

                if (presenter.getRemovedList().isNotEmpty())
                     presenter.redoOperation()
            }
        }
    }

    override fun updateResultAndOpList(list: ArrayList<Calculator>) {

        if (presenter.getList().isNotEmpty())
            btn_undo.isClickable = true

        if (presenter.getRemovedList().isNotEmpty())
            btn_redo.isClickable = true

        adapter.setItems(list)
        gv_operations.adapter = adapter

        et_operand.setText("")
        tv_result.text = resources.getText(R.string.result).toString().plus(if (list.isNotEmpty()) list[list.lastIndex].result else 0)
    }
}