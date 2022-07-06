package com.ez.dotarate.view.upcominggames.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ez.dotarate.R
import com.ez.dotarate.databinding.CategoryUgItemBinding

class CategoryUGAdapter(
    private val value: Map<Int, String>,
    private val clickListener: (Int) -> Unit
) : RecyclerView.Adapter<CategoryUGAdapter.CategoryUGHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryUGHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<CategoryUgItemBinding>(
            inflater,
            R.layout.category_ug_item,
            parent,
            false
        )

        return CategoryUGHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryUGHolder, position: Int) =
        holder.bind(value.values.toList()[position])

    override fun getItemCount() = value.size

    inner class CategoryUGHolder(private val binding: CategoryUgItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryName: String) {
            binding.tvCategoryName.text = categoryName

            binding.tvCategoryName.setOnClickListener {
                clickListener.invoke(value.filterValues { categoryName ->
                    categoryName == (it as TextView).text.toString()
                }.keys.first())
            }
        }
    }
}