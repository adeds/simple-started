package id.adeds.started.view

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import id.adeds.started.R
import id.adeds.started.data.model.User
import id.adeds.started.util.Status
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()
    private val list: MutableList<User> = mutableListOf()
    private lateinit var mainAdapter: MainAdapter

    override fun onStart() {
        super.onStart()
        mainAdapter = MainAdapter(list)
        rvUser.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }
        setupViewModel()
        viewModel.call()
    }

    private fun setupViewModel() = viewModel.apply {
        results.observe(this@MainActivity) { result ->
            when (result.status) {
                Status.LOADING -> {
                    mainFlipper.displayedChild = 0
                }
                Status.SUCCESS -> {
                    mainFlipper.displayedChild = 1
                    result.data?.let { list.addAll(it) }
                    Log.d("mainData", result.data.toString())
                    mainAdapter.notifyDataSetChanged()
                }
                Status.FAILED -> {
                    mainFlipper.displayedChild = 2
                    Log.e("mainError", result.message?:"null error")
                }
            }
        }
    }
}