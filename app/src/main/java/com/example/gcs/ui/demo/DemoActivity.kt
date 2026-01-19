package com.example.gcs.ui.demo

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gcs.data.local.AppDatabase
import com.example.gcs.data.remote.ApiService
import com.example.gcs.data.repository.DemoRepository
import com.example.gcs.databinding.ActivityDemoBinding

object Injector {
    fun provideDemoViewModelFactory(context: Context): DemoViewModelFactory {
        val repository = DemoRepository(
            ApiService.create(),
            AppDatabase.getDatabase(context.applicationContext).demoItemDao()
        )
        return DemoViewModelFactory(repository)
    }
}

class DemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDemoBinding
    private val viewModel: DemoViewModel by viewModels {
        Injector.provideDemoViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = DemoAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        observeViewModel(adapter)
    }

    private fun observeViewModel(adapter: DemoAdapter) {
        viewModel.items.observe(this) { items ->
            binding.progressBar.isVisible = items.isNullOrEmpty()
            adapter.submitList(items)
        }
    }
}
