package com.example.peduliternak.view

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peduliternak.data.response.PredictionsItem
import com.example.peduliternak.databinding.ItemHistoryBinding

class HistoryAdapter : ListAdapter<PredictionsItem, HistoryAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.binding.root.setOnClickListener {
            onItemClickCallback?.onItemClicked(user)
        }
        holder.bind(user)
    }

    inner class MyViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PredictionsItem) {

            Log.d(ContentValues.TAG, "bind: ${item}")
            binding.tvItem.text = item.result
//            binding.tvItem.text = "${user.login}"
//
            Glide.with(binding.root.context)
                .load(item.imageUrl)
                .circleCrop()
                .into(binding.imgItemPhoto)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: PredictionsItem)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PredictionsItem>() {
            override fun areItemsTheSame(
                oldItem: PredictionsItem,
                newItem: PredictionsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PredictionsItem,
                newItem: PredictionsItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}