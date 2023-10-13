package com.test.kerja.frengit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.kerja.frengit.model.TabUserDetail
import com.test.kerja.frengit.databinding.ItemRowLabBinding
import com.test.kerja.frengit.ui.onItemClickCallbackGit

class DetailGithubAdapter(private val tabUserDetail:ArrayList<TabUserDetail>): RecyclerView.Adapter<DetailGithubAdapter.MyViewHolder>() {
    private lateinit var onItemClickCallbackGit: onItemClickCallbackGit
    class MyViewHolder(val binding: ItemRowLabBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowLabBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return tabUserDetail.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(tabUserDetail[position]){
                binding.tvItemName.text = "${this.login}"
                binding.tvItemDetail.text = "${this.id}"
                Glide.with(itemView.context)
                    .load(this.avatar_url)
                    .into(binding.ivCircle)
            }
        }
//        holder.itemView.setOnClickListener { onItemClickCallbackGit.onItemClickLabs(tabUserDetail[holder.adapterPosition]) }

    }
    fun setOnItemClickCallbackLabs(onItemClickCallbackGit: onItemClickCallbackGit){
        this.onItemClickCallbackGit = onItemClickCallbackGit
    }
}