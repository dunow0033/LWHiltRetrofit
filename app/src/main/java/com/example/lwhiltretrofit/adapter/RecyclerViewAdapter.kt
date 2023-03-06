package com.example.lwhiltretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lwhiltretrofit.databinding.RepositoryListRowBinding
import com.example.lwhiltretrofit.model.RepositoryData

class RecyclerViewAdapter(): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var listData: List<RepositoryData>? = null

    fun setListData(listData: List<RepositoryData>?) {
        this.listData = listData
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        return MyViewHolder(
            RepositoryListRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listData == null) return 0
        return listData?.size!!
    }

    class MyViewHolder(val binding: RepositoryListRowBinding): RecyclerView.ViewHolder(binding.root) {

        val image_avatar = binding.imageAvatarUrl
        val tvName = binding.tvName
        val tvDesc = binding.tvDesc

        fun bind(data: RepositoryData){
            tvName.text = data.name
            tvDesc.text = data.description

            Glide.with(image_avatar)
                .load(data.owner?.avatar_url)
                .into(image_avatar)
        }
    }
}