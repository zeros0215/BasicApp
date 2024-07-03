package com.study.basicapp.ui.remotelist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.study.basicapp.R

class RemoteListAdapter : RecyclerView.Adapter<RemoteListAdapter.ViewHolder>() {

    private val TAG = "RemoteListAdapter"
    private lateinit var itemView : View
    private var items : List<UserItem> = ArrayList()

    private lateinit var listener: RemoteListAdapter.OnItemClickListener
    interface OnItemClickListener{
        fun OnClick(v: View, position: Int)
        fun OnIconClick(v: View, position: Int)
    }
    fun setOnItemClickListener(onitemclicklistener: OnItemClickListener){
        listener = onitemclicklistener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoteListAdapter.ViewHolder {

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

        holder.favorite.setOnClickListener{
            listener.OnIconClick(it, position)
        }

        holder.itemView.setOnClickListener {
            listener.OnClick(it, position)
        }



    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val number: TextView
        val favorite : ImageButton

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

    fun getItem(): List<UserItem>{
        return items
    }

    fun setItem(item: List<UserItem>){
        items = item
    }

    fun getItem(position : Int): UserItem {
        return items[position]
    }





}