package com.example.customcalculator.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.customcalculator.R
import com.example.customcalculator.models.Calculator

class OperationAdapter(private val context : Context) : BaseAdapter() {

    private var list: ArrayList<Calculator> = ArrayList()

    override fun getCount(): Int = list.size

    override fun getItem(p0: Int): Any? = null

    override fun getItemId(p0: Int): Long = 0

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_operation,parent,false)
        val op = view.findViewById<TextView>(R.id.tv_op)
        val item = list[position]
        op.text = item.op?.plus(" ").plus(item.operand)
        return view
    }

    fun setItems(list: ArrayList<Calculator>){
        this.list = list
    }
}