package com.example.codingthoughtctl.ui_layer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.codingthoughtctl.R
import com.example.codingthoughtctl.data_layer.remote.models.Data
import com.example.codingthoughtctl.databinding.ImageCustomRowBinding
import getFormattedDate
import javax.inject.Inject

class TopImageAdapter @Inject constructor(
    private val imgList: List<Data>
) :
    ListAdapter<Data, TopImageAdapter.TopImageViewHolder>(TopImageListDiffUtil()), Filterable {

    private var binding: ImageCustomRowBinding? = null

    private lateinit var imgFilteredList: List<Data>

    init {
        imgFilteredList = imgList
    }

    inner class TopImageViewHolder(private val binding: ImageCustomRowBinding) :
        ViewHolder(binding.root) {
        fun bind(imgInfo: Data) {
            Glide.with(binding.imgPoster).load(
                binding.root.context.getString(
                    R.string.load_img_url,
                    imgInfo.cover
                )
            )
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

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                if (p0.isNullOrEmpty()) {
                    imgFilteredList = imgList
                } else {
                    val filterList = mutableListOf<Data>()
                    imgList?.forEach {
                        if (it.title?.lowercase()?.contains(
                                p0.toString().lowercase()
                            ) == true || it.description?.lowercase()
                                ?.contains(p0.toString().lowercase()) == true
                        ) {
                            filterList.add(it)
                        }
                    }
                    imgFilteredList = filterList
                }

                return FilterResults().apply {
                    values = imgFilteredList
                }
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                imgFilteredList = (p1?.values as? List<Data>)!!
                submitList(imgFilteredList)
            }

        }
    }

    fun onFilter(list: List<Data>, constraint: String): List<Data> {

        val filteredList = list.filter {

            it.title?.lowercase()
                ?.contains(constraint.lowercase()) == true || it.description?.lowercase()
                ?.contains(constraint.lowercase()) == true
        }

        return filteredList

    }

}