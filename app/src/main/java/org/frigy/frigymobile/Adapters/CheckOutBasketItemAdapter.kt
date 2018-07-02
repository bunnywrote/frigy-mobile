package org.frigy.frigymobile.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import org.frigy.frigymobile.Extensions.DateTimeExtensions
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.R


class CheckOutBasketItemAdapter: BaseAdapter {

    private var itemsList: List<Item> = listOf()
    private var context: Context? = null

    constructor(context: Context, items: List<Item>) : super() {
        this.itemsList = items
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: CheckOutItemViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.check_out_item, parent, false)
            vh = CheckOutItemViewHolder(view)
            view.tag = vh
            Log.i("JSA", "set Tag for ViewHolder, position: " + position)
        } else {
            view = convertView
            vh = view.tag as CheckOutItemViewHolder
        }

        val item = itemsList!!.get(position)

        vh.productTitle.text = item?.product?.title
        vh.itemCreated.text = DateTimeExtensions.toSimpleString(itemsList!!.get(position)!!.created)
        vh.itemSelected.isChecked = item?.isSelected

        return view
    }

    override fun getItem(position: Int): Item? {
        return itemsList!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return itemsList!!.size
    }

    fun setSelected(position: Int){
        this.itemsList.get(position).isSelected = !this.itemsList.get(position).isSelected
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<Item>){
        this.itemsList = newItems
        notifyDataSetChanged()
    }
}

private class CheckOutItemViewHolder(view: View?) {
    val productTitle: TextView
    val itemCreated: TextView
    val itemSelected: CheckBox

    init {
        this.productTitle = view?.findViewById<TextView>(R.id.productTitle) as TextView
        this.itemCreated = view?.findViewById<TextView>(R.id.itemCreated) as TextView
        this.itemSelected = view?.findViewById<CheckBox>(R.id.itemSelected) as CheckBox
    }
}