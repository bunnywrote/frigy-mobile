package org.frigy.frigymobile.Adapters

import android.arch.lifecycle.LiveData
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.frigy.frigymobile.Extensions.DateTimeExtensions.Companion.toSimpleString
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.R

class SearchItemAdapter : BaseAdapter {

    private var itemsList: LiveData<List<Item>>
    private var context: Context? = null

    constructor(context: Context, items: LiveData<List<Item>>) : super() {
        this.itemsList = items
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
            Log.i("JSA", "set Tag for ViewHolder, position: " + position)
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        vh.productTitle.text = itemsList.value?.get(position)?.product?.title
        vh.itemCreated.text = toSimpleString(itemsList.value?.get(position)!!.created);

        return view
    }

    override fun getItem(position: Int): Item? {
        return itemsList.value?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return itemsList.value!!.size;
    }

    fun replaceItems(newItems: LiveData<List<Item>>){
    //    Thread(Runnable {
            this.itemsList = newItems

            notifyDataSetChanged();
        //  })
    }
}

private class ViewHolder(view: View?) {
    val productTitle: TextView
    val itemCreated: TextView

    init {
        this.productTitle = view?.findViewById<TextView>(R.id.productTitle) as TextView
        this.itemCreated = view?.findViewById<TextView>(R.id.itemCreated) as TextView
    }
}