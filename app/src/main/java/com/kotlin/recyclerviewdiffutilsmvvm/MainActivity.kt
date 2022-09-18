package com.kotlin.recyclerviewdiffutilsmvvm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.recyclerviewdiffutilsmvvm.adapters.AdapterMain
import com.kotlin.recyclerviewdiffutilsmvvm.databinding.ActivityMainBinding
import com.kotlin.recyclerviewdiffutilsmvvm.interfaces.OnItemClickListener
import com.kotlin.recyclerviewdiffutilsmvvm.models.Student
import com.kotlin.recyclerviewdiffutilsmvvm.viewModels.ViewModelMain

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: ViewModelMain by viewModels()

    private lateinit var adapter: AdapterMain
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
        fetchList()

        binding.fabAddMain.setOnClickListener { onUpdateClick() }
    }

    private fun initRecyclerView() {
        val itemClickListener = object : OnItemClickListener {
            override fun onItemClick(adapterPosition: Int) {
                Toast.makeText(this@MainActivity, studentList[adapterPosition].name, Toast.LENGTH_SHORT).show()
            }
        }
        adapter = AdapterMain(itemClickListener)
        binding.rvMain.adapter = adapter
    }

    private fun fetchList() {
        viewModel.fetchList()
        viewModel.studentListLiveData.observe(this) {
            studentList = it
            adapter.submitList(it)
        }
    }

    private fun onUpdateClick() {
        viewModel.fetchList()
    }
}