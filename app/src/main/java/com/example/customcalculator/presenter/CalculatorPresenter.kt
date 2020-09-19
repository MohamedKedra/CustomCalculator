package com.example.customcalculator.presenter

import com.example.customcalculator.models.Calculator

class CalculatorPresenter(private val view: CalculatorContract.View) : CalculatorContract.Presenter{

    private var list : ArrayList<Calculator> = ArrayList()
    private var removedList : ArrayList<Calculator> = ArrayList()
    private lateinit  var calculator: Calculator

    override fun setOperator(op: String) {
        calculator = Calculator()
        if(list.isNotEmpty())
        calculator.result = list[list.lastIndex].result
        calculator.op = op
    }

    override fun completeCalculation(operand: String) {
        if (!calculator.op.isNullOrEmpty()) {
            calculator.operand = operand.toInt()
            list.add(getResult())
            calculator = Calculator()
            view.updateResultAndOpList(list)
        }
    }

    override fun undoOperation() {
        removedList.add(list.removeLast())
        view.updateResultAndOpList(list)
    }

    override fun redoOperation() {
        list.add(removedList.removeLast())
        view.updateResultAndOpList(list)
    }

    override fun getList(): ArrayList<Calculator> = list

    override fun getRemovedList(): ArrayList<Calculator> = removedList

    private fun getResult() : Calculator{
        when(calculator.op){

            "+" -> calculator.result += calculator.operand
            "-" -> calculator.result -= calculator.operand
            "*" -> calculator.result *= calculator.operand
            "/" -> calculator.result /= calculator.operand
        }
        return calculator
    }


}