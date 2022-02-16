package com.maximapps.maxim_weather.ui.list

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class ListAdapter<T> : RecyclerView.Adapter<ListAdapter.ViewHolder<T>>() {
    protected val list = mutableListOf<T>()

    abstract fun setData(data: List<T>)

    abstract class ViewHolder<T>(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: T)
    }

}

fun <T> listAdapter(init: (parent: ViewGroup) -> ListAdapter.ViewHolder<T>) =
    object : ListAdapter<T>() {
        @SuppressLint("NotifyDataSetChanged")
        override fun setData(data: List<T>) {
            list.clear()
            list.addAll(data)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = init(parent)

        override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
            holder.bind(list[position])
        }

        override fun getItemCount() = list.size

    }


fun <T> listAdapterWithViewTypes(
    init: (parent: ViewGroup, viewType: Int) -> ListAdapter.ViewHolder<T>,
    itemViewType: (position: Int) -> Int
) =
    object : ListAdapter<T>() {
        @SuppressLint("NotifyDataSetChanged")
        override fun setData(data: List<T>) {
            list.clear()
            list.addAll(data)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = init(parent, viewType)

        override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
            holder.bind(list[position])
        }

        override fun getItemCount() = list.size

        override fun getItemViewType(position: Int) = itemViewType(position)
    }
