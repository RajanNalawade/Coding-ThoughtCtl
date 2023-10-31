package com.example.codingthoughtctl.ui_layer.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.codingthoughtctl.R
import com.example.codingthoughtctl.data_layer.remote.models.Data
import com.example.codingthoughtctl.databinding.ImageCustomRowBinding
import getFormattedDate
import javax.inject.Inject

class TopImageAdapter @Inject constructor() :
    ListAdapter<Data, TopImageAdapter.TopImageViewHolder>(TopImageListDiffUtil()) {

    private var binding: ImageCustomRowBinding? = null

    inner class TopImageViewHolder(private val binding: ImageCustomRowBinding) :
        ViewHolder(binding.root) {
        fun bind(imgInfo: Data) {
            Glide.with(binding.imgPoster).load("https://i.imgur.com/${imgInfo.cover}.jpg")
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(binding.imgPoster)
            binding.imgTitle.text = imgInfo.title
            binding.imgDate.text = imgInfo.datetime?.getFormattedDate()
            binding.imgNumbers.text = "${imgInfo.imagesCount ?: 0}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopImageViewHolder {
        this.binding =
            ImageCustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopImageViewHolder(this.binding!!)
    }

    override fun onBindViewHolder(holder: TopImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TopImageListDiffUtil : ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }

}