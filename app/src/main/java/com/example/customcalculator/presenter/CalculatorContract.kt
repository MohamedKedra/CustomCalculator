package com.example.customcalculator.presenter

import com.example.customcalculator.models.Calculator

class CalculatorContract {

    interface View{

        fun updateResultAndOpList(list: ArrayList<Calculator>)
    }

    interface Presenter{

        fun setOperator(op :String)

        fun completeCalculation(operand : String)

        fun undoOperation()
    }
}