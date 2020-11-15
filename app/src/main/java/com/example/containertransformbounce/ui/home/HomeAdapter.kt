package com.example.containertransformbounce.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.containertransformbounce.model.Sample
import com.example.containertransformbounce.databinding.SampleItemBinding

class HomeAdapter(private val listener: HomeAdapterListener) : ListAdapter<Sample, HomeAdapter.HomeViewHolder>(SampleDiffUtil) {

    interface HomeAdapterListener {
        fun onSampleItemClick(view: View, sample: Sample)
    }

    object SampleDiffUtil: DiffUtil.ItemCallback<Sample>() {
        override fun areItemsTheSame(oldItem: Sample, newItem: Sample): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Sample, newItem: Sample): Boolean =
            oldItem.description == newItem.description && oldItem.title == newItem.title
    }

    class HomeViewHolder(private val binding: SampleItemBinding, private val listener: HomeAdapterListener): RecyclerView.ViewHolder(binding.root) {

        fun bind(sample: Sample) {
            binding.run {
                this.sample = sample
                this.listener = this@HomeViewHolder.listener
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(SampleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bind(getItem(position))
}