package com.vasya.numberlistapp
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var adapter: NumberAdapter
    private lateinit var viewModel: NumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupViewModel()
        setupRecyclerView()
        setupClickListeners()
        observeNumbers()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        fab = findViewById(R.id.fab)
        adapter = NumberAdapter()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[NumberViewModel::class.java]
    }

    private fun setupRecyclerView() {
        val spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            resources.getInteger(R.integer.landscape_columns)
        } else {
            resources.getInteger(R.integer.portrait_columns)
        }

        recyclerView.layoutManager = GridLayoutManager(this, spanCount)
        recyclerView.adapter = adapter
    }

    private fun setupClickListeners() {
        fab.setOnClickListener {
            viewModel.addNumber()
        }
    }

    private fun observeNumbers() {
        viewModel.numbers.observe(this) { numbersList ->
            adapter.updateNumbers(numbersList)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setupRecyclerView()
    }
}