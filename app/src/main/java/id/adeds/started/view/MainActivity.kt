package id.adeds.started.view

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import id.adeds.started.R
import id.adeds.started.util.Status
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()

    override fun onStart() {
        super.onStart()
        setupViewModel()
        viewModel.call("1")
    }

    private fun setupViewModel() = viewModel.apply {
        results.observe(this@MainActivity) { result ->
            when (result.status) {
                Status.LOADING -> {
                    mainFlipper.displayedChild = 0
                }
                Status.SUCCESS -> {
                    mainFlipper.displayedChild = 1
                    textResults.text = result.data?.name
                }
                Status.FAILED -> {
                    mainFlipper.displayedChild = 1
                    textResults.text = result.message
                }
            }
        }
    }
}