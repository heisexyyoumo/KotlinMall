package com.kotlin.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.goods.R
import com.kotlin.goods.data.protocol.Category
import kotlinx.android.synthetic.main.layout_top_category_item.view.*

class TopCategoryAdapter(context: Context):BaseRecyclerViewAdapter<Category,
        TopCategoryAdapter.ViewHolder>(context){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_top_category_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.mTopCategoryNameTv.text  = model.categoryName
        holder.itemView.mTopCategoryNameTv.isSelected = model.isSelected
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        init {

        }
    }
}