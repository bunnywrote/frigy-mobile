package org.frigy.frigymobile.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.R
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import org.frigy.frigymobile.ViewModels.CheckinBasketViewModel


class CheckinBasketItemAdapter(context: Context, private val viewModel: CheckinBasketViewModel) : RecyclerView.Adapter<CheckinBasketItemAdapter.CheckinBasketItemViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var itemCache: MutableList<Item> = mutableListOf()

    class CheckinBasketItemViewHolder(basketItemView: View) : RecyclerView.ViewHolder(basketItemView) {
        val itemTitle: TextView = basketItemView.findViewById(R.id.item_title)
        val itemInfo: TextView = basketItemView.findViewById(R.id.item_info)
        val itemImage: ImageView = basketItemView.findViewById(R.id.item_image)
        val removeButton: ImageButton = basketItemView.findViewById(R.id.remove_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CheckinBasketItemViewHolder {
        var itemView: View = mInflater.inflate(R.layout.checkin_basket_item_layout, parent, false)
        return CheckinBasketItemViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: CheckinBasketItemViewHolder, position: Int) {
        val currentItem: Item = itemCache.get(position)
        holder.itemTitle.setText(currentItem.product.title)
        holder.itemInfo.setText(currentItem.itemId.toString() + " " + currentItem.created.toString())
        holder.removeButton.setOnClickListener({ _ ->
            removeItem(position)
        })
    }

    fun updateItems(items: List<Item>) {
        itemCache = items.toMutableList()
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        viewModel.removeItem(itemCache.removeAt(position))
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    fun addItem(item: Item) {
        itemCache.add(item)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return itemCache.size
    }


}