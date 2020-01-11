package com.monodukuri.coodinateapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    private val context: Context,
    private val itemClickListener: RecyclerViewHolder.ItemClickListener,
    private val itemList: List<String>
) : RecyclerView.Adapter<RecyclerViewHolder>() {
    private var mRecyclerView: RecyclerView? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {


        val layoutInflater = LayoutInflater.from(context)
        val mView = layoutInflater.inflate(R.layout.list_item, parent, false)

        mView.setOnClickListener { view ->
            mRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }

        return RecyclerViewHolder(mView)
    }
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder?.let {
            it.itemTextView.text = itemList.get(position)
            it.itemImageView.setImageResource(R.mipmap.ic_launcher)
        }
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        if (recyclerView != null) {
            super.onAttachedToRecyclerView(recyclerView)
        }
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        if (recyclerView != null) {
            super.onDetachedFromRecyclerView(recyclerView)
        }
        mRecyclerView = null

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}