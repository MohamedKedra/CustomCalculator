package com.example.customcalculator.presenter

import com.example.customcalculator.models.Calculator

class CalculatorPresenter(private val view: CalculatorContract.View) : CalculatorContract.Presenter{

    private var list : ArrayList<Calculator> = ArrayList()
    private lateinit  var calculator: Calculator

    override fun setOperator(op: String) {
        calculator = Calculator()
        if(list.isNotEmpty())
        calculator.result = list[list.lastIndex].result
        calculator.op = op
    }

    override fun completeCalculation(operand: String) {
        calculator.operand = operand.toInt()
        list.add(getResult())
        view.updateResultAndOpList(list)
    }

    override fun undoOperation() {
        list.removeLast()
        view.updateResultAndOpList(list)
    }

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