package com.example.homeworktwelve

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktwelve.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var products = mutableListOf<Product>()
    private lateinit var adapter: ProductRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setProductData()

        setUpRowRecycler()
        val searchQuery = ""
        adapter.filter(searchQuery)


    }

    private fun setUpRowRecycler() {
        adapter = ProductRecyclerAdapter()
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        adapter.setData(products)

    }

    private fun setProductData() {
        products.also {

            it.add(Product(1, R.drawable.vase1, "vase one", 2.3f, 2003, 123.4, "this bla"))
            it.add(Product(2, R.drawable.vase2, "vase two", 22.3f, 2003, 1232.4, "this bla"))
            it.add(Product(3, R.drawable.vase3, "vase three", 231.3f, 2003, 123312.4, "this bla"))
        }
    }
}

