package com.test.kerja.frengit.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.kerja.frengit.model.ItemsItem
import com.test.kerja.frengit.databinding.ItemRowLabBinding
import com.test.kerja.frengit.ui.DetailUserActivity

class GithubAdapter : ListAdapter<ItemsItem, GithubAdapter.MyViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

        }
    }

    class MyViewHolder(private val binding: ItemRowLabBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(i: ItemsItem) {
            binding.tvItemName.text = "${i.login}"
            binding.tvItemDetail.text = "${i.id}"
            Glide.with(itemView.context)
                .load(i.avatar_url)
                .into(binding.ivCircle)
            itemView.setOnClickListener {
                val intent = Intent(it.context, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.DETAILUSER,i)
                it.context.startActivity(intent)
                Toast.makeText(it.context, i.login, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowLabBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val a = getItem(position)
        holder.bind(a)

    }
}