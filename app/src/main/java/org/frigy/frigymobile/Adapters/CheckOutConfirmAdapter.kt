package org.frigy.frigymobile.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.R


class CheckOutConfirmAdapter : BaseAdapter{

    private var itemsList: MutableList<Item> = mutableListOf()
    private var context: Context? = null

    constructor(context: Context, items: MutableList<Item>) : super() {
        this.itemsList = items
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: CheckinBasketItemAdapter.CheckinBasketItemViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.checkin_basket_item_layout, parent, false)
            vh = CheckinBasketItemAdapter.CheckinBasketItemViewHolder(view)
            view.tag = vh
            Log.i("JSA", "set Tag for ViewHolder, position: " + position)
        } else {
            view = convertView
            vh = view.tag as CheckinBasketItemAdapter.CheckinBasketItemViewHolder
        }

        val item = itemsList!!.get(position)

        vh.itemTitle.setText(item.product.title)
        vh.itemInfo.setText(item.itemId.toString() + " " + item.created.toString())
        vh.removeButton.setOnClickListener({ _ ->
            //Toast.makeText(context, itemsList.get(position)?.product?.title,Toast.LENGTH_SHORT).show()
            remove(position)
        })

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

    fun remove(position: Int){
        this.itemsList.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItems(newItems: MutableList<Item>){
        this.itemsList = newItems
        notifyDataSetChanged()
    }
}