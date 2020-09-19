package com.example.customcalculator.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import com.example.customcalculator.R
import com.example.customcalculator.models.Calculator
import com.example.customcalculator.presenter.CalculatorContract
import com.example.customcalculator.presenter.CalculatorPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener ,CalculatorContract.View{

    private lateinit var presenter: CalculatorPresenter
    private lateinit var adapter: OperationAdapter
    private var selectedView : View? = null

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

        gv_operations.visibility = View.GONE
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
                    btn_equal.isClickable = text.isNotEmpty() && selectedView != null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    override fun onClick(view: View?) {

        when (view?.id) {

            btn_add.id -> {

                implAction(btn_add)
            }
            btn_sub.id -> {
                implAction(btn_sub)
            }
            btn_mul.id -> {
                implAction(btn_mul)
            }
            btn_div.id -> {
                implAction(btn_div)
            }
            btn_equal.id -> {
                presenter.completeCalculation(et_operand.text.toString())
                deselectOp()
                hideKeyboard()
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

    private fun implAction(view: Button){
        if(selectedView != null) deselectOp()
        view.background = resources.getDrawable(R.drawable.draw_op_selected)
        presenter.setOperator(view.text.toString())
        selectedView = view

        et_operand.text?.let {
            if (it.toString().isNotEmpty()) btn_equal.isClickable = true
        }
    }

    override fun updateResultAndOpList(list: ArrayList<Calculator>) {

        if (presenter.getList().isNotEmpty()) {
            btn_undo.isClickable = true
            gv_operations.visibility = View.VISIBLE
        }
        else
            gv_operations.visibility = View.GONE

        if (presenter.getRemovedList().isNotEmpty())
            btn_redo.isClickable = true

        adapter.setItems(list)
        gv_operations.adapter = adapter

        et_operand.setText("")
        tv_result.text = resources.getText(R.string.result).toString().plus(if (list.isNotEmpty()) list[list.lastIndex].result else 0)
    }

    private fun hideKeyboard(){

        val inputMethodManager : InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken,0)
    }

    private fun deselectOp(){

        when(selectedView?.id){
            btn_add.id -> {
                btn_add.background = null
            }
            btn_sub.id -> {
                btn_sub.background = null
            }
            btn_mul.id -> {
                btn_mul.background = null
            }
            btn_div.id -> {
                btn_div.background = null
            }
        }

        selectedView = null
    }
}