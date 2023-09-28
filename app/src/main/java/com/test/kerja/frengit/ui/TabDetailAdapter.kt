package com.test.kerja.frengit.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.kerja.frengit.data.response.TabUserDetail
import com.test.kerja.frengit.databinding.ItemRowLabBinding

class TabDetailAdapter: ListAdapter<TabUserDetail, TabDetailAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowLabBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val a = getItem(position)
        holder.bind(a)
    }
    class MyViewHolder(private val binding: ItemRowLabBinding) :
        RecyclerView.ViewHolder(binding.root)  {
        fun bind(i: TabUserDetail) {
            binding.tvItemName.text = "${i.login}"
            binding.tvItemDetail.text = "${i.id}"
            Glide.with(itemView.context)
                .load(i.avatar_url)
                .into(binding.ivCircle)
        }


    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TabUserDetail>() {
            override fun areItemsTheSame(oldItem: TabUserDetail, newItem: TabUserDetail): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TabUserDetail, newItem: TabUserDetail): Boolean {
                return oldItem == newItem
            }

        }
    }
}