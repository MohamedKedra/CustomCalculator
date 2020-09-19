package com.example.customcalculator

import com.example.customcalculator.models.Calculator
import com.example.customcalculator.presenter.CalculatorContract
import com.example.customcalculator.presenter.CalculatorPresenter

import org.junit.Test
import org.junit.Assert.*


class CalculatorTest : CalculatorContract.View {

    var presenter: CalculatorPresenter = CalculatorPresenter(this)

    @Test
    fun addition_isCorrect(){

        presenter.setOperator("+")
        presenter.completeCalculation("2")
        assertEquals(2, presenter.getList()[0].result)


        presenter.setOperator("+")
        presenter.completeCalculation("8")

        assertEquals(10, presenter.getList()[1].result)
    }

    @Test
    fun subtract_isCorrect(){

        presenter.setOperator("+")
        presenter.completeCalculation("2")
        assertEquals(2, presenter.getList()[0].result)


        presenter.setOperator("-")
        presenter.completeCalculation("8")

        assertEquals(-6, presenter.getList()[1].result)
    }

    @Test
    fun multiple_isCorrect(){

        presenter.setOperator("+")
        presenter.completeCalculation("2")
        assertEquals(2, presenter.getList()[0].result)


        presenter.setOperator("*")
        presenter.completeCalculation("8")

        assertEquals(16, presenter.getList()[1].result)
    }

    @Test
    fun divide_isCorrect(){

        presenter.setOperator("+")
        presenter.completeCalculation("12")
        assertEquals(12, presenter.getList()[0].result)


        presenter.setOperator("/")
        presenter.completeCalculation("4")

        assertEquals(3, presenter.getList()[1].result)
    }

    @Test
    fun undo_isCorrect(){

        presenter.setOperator("+")
        presenter.completeCalculation("12")
        assertEquals(12, presenter.getList()[0].result)

        presenter.setOperator("*")
        presenter.completeCalculation("3")
        assertEquals(36, presenter.getList()[1].result)

        presenter.undoOperation()
        assert(presenter.getList().size == 1)
    }

    @Test
    fun redo_isCorrect(){

        presenter.setOperator("+")
        presenter.completeCalculation("12")
        assertEquals(12, presenter.getList()[0].result)

        presenter.setOperator("*")
        presenter.completeCalculation("3")
        assertEquals(36, presenter.getList()[1].result)

        presenter.undoOperation()
        assert(presenter.getList().size == 1)

        presenter.redoOperation()
        assert(presenter.getList().size == 2)
    }

    @Test
    fun example_isCorrect(){

        presenter.setOperator("+")
        presenter.completeCalculation("3")
        assertEquals(3, presenter.getList()[0].result)

        presenter.setOperator("+")
        presenter.completeCalculation("2")
        assertEquals(5, presenter.getList()[1].result)

        presenter.setOperator("*")
        presenter.completeCalculation("5")
        assertEquals(25, presenter.getList()[2].result)

        presenter.undoOperation()
        presenter.undoOperation()
        assert(presenter.getList().size == 1)
        assertEquals(3,presenter.getList()[0].result)

        presenter.redoOperation()
        assert(presenter.getList().size == 2)
        assertEquals(5,presenter.getList()[1].result)

        presenter.setOperator("+")
        presenter.completeCalculation("3")
        assertEquals(8, presenter.getList()[2].result)


        presenter.undoOperation()
        presenter.undoOperation()
        presenter.undoOperation()
        presenter.undoOperation()
        assertEquals(25,presenter.getList()[0].result)
    }

    override fun updateResultAndOpList(list: ArrayList<Calculator>) {
    }
}