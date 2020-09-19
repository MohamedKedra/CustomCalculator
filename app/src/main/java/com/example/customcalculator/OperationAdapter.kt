package com.example.customcalculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class OperationAdapter(private val context : Context) : BaseAdapter() {

    override fun getCount(): Int = 9

    override fun getItem(p0: Int): Any? = null

    override fun getItemId(p0: Int): Long = 0

    override fun getView(p0: Int, view: View?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.item_operation,parent,false)
    }
}