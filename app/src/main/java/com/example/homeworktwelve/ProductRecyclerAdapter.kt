package com.example.homeworktwelve

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.homeworktwelve.databinding.ItemRecyclerviewBinding

class ProductRecyclerAdapter() : ListAdapter<Product, ProductRecyclerAdapter.ProductViewHolder>(
    object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
) {
    private var productList: List<Product> = listOf() // Original list

    fun setData(products: List<Product>) {
        productList = products
        submitList(products)
    }

    interface OnProductClickListener {
        fun onProductClick(product: Product)
    }

    private var productClickListener: OnProductClickListener? = null

    fun setOnProductClickListener(listener: OnProductClickListener) {
        productClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(private val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    productClickListener?.onProductClick(getItem(position))
                }
            }
        }
        fun bind(product: Product) {
            binding.apply {
                ivbImage.setBackgroundResource(product.image)
                tvTitlee.text = product.title
                tvRate.text = product.rate.toString()
                tvquantity.text = product.quantitySold.toString()
                tvPrice.text = product.price.toString()
            }
        }
    }

    fun filter(query: String) {
        val filteredList = if (query.isBlank()) {
            productList // Show the original list if the query is empty
        } else {
            productList.filter { it.title.contains(query, ignoreCase = true) }
        }
        submitList(filteredList)
    }
}