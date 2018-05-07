package org.frigy.frigymobile.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.R

class SearchItemAdapter : BaseAdapter {

    private var itemsList = ArrayList<Item>()
    private var context: Context? = null

    constructor(context: Context, notesList: ArrayList<Item>) : super() {
        this.itemsList = notesList
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

        vh.productTitle.text = itemsList[position].product.title
        vh.itemCreated.text = itemsList[position].created.toString()

        return view
    }

    override fun getItem(p0: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(p0: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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