package com.study.basicapp.ui.remotelist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.study.basicapp.R
import com.study.basicapp.ui.remotelist.model.user_item

class LocalListAdapter : RecyclerView.Adapter<LocalListAdapter.ViewHolder>() {

    private val TAG = "LocalListAdapter"
    private lateinit var itemView : View
    private var items : List<user_item> = ArrayList()


    private lateinit var listener: LocalListAdapter.OnItemClickListener
    interface OnItemClickListener{
        fun OnClick(v: View, position: Int)
        fun OnIconClick(v: View, position: Int)
    }
    fun setOnItemClickListener(onitemclicklistener: OnItemClickListener){
        listener = onitemclicklistener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalListAdapter.ViewHolder {

        itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_user,
            parent,
            false
        )

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "position: " + position)
        Log.d(TAG, "items[position].name: " + items[position].name)

        holder.name.text = items[position].name
        holder.number.text = items[position].number

        holder.itemView.setOnClickListener {
            listener.OnClick(it, position)
        }

        holder.favorite.setOnClickListener {
            listener.OnIconClick(it, position)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val number: TextView
        val favorite: ImageButton

        init {
            // Define click listener for the ViewHolder's View
            name = itemView.findViewById(R.id.name)
            number = itemView.findViewById(R.id.number)
            favorite = itemView.findViewById(R.id.favorite)
        }

    }


    override fun getItemCount(): Int {
        Log.d(TAG, "items.size: " + items.size)
        return items.size
    }

    fun getItem(): List<user_item>{
        return items
    }

    fun setItem(item: List<user_item>){
        items = item
    }

    fun getItem(position : Int): user_item {
        return items[position]
    }





}