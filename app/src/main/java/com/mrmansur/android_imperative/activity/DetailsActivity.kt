package com.mrmansur.android_imperative.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mrmansur.android_imperative.R
import com.mrmansur.android_imperative.adapter.TVShortAdapter
import com.mrmansur.android_imperative.databinding.ActivityDetailsBinding
import com.mrmansur.android_imperative.mvvm.DetailsViewModel
import com.mrmansur.android_imperative.utils.Logger

class DetailsActivity : BaseActivity() {
    private val TAG = "DetailsActivity"
    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        initObserves()

        binding.rvShorts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        val iv_detail: ImageView = binding.ivDetail
        binding.ivClose.setOnClickListener {
            ActivityCompat.finishAfterTransition(this)
        }

        val extras = intent.extras
        val show_id = extras!!.getLong("show_id")
        val show_img = extras.getString("show_img")
        val show_name = extras.getString("show_name")
        val show_network = extras.getString("show_network")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName = extras.getString("iv_movie")
            iv_detail.transitionName = imageTransitionName
        }

        binding.tvName.text = show_name
        binding.tvType.text = show_network
        Glide.with(this).load(show_img).into(iv_detail)

        viewModel.apiTVShowDetails(show_id.toInt())    }

    private fun initObserves() {
        // Retrofit Related
        viewModel.tvShowDetails.observe(this) {
            Logger.d(TAG, it.toString())
            refreshAdapter(it.tvShow.pictures)
            binding.tvDetails.text = it.tvShow.description
        }
        viewModel.errorMessage.observe(this) {
            Logger.d(TAG, it.toString())
        }
        viewModel.isLoading.observe(this, Observer {
            Logger.d(TAG, it.toString())
            if (viewModel.isLoading.value == true) {
                binding.pbLoadingd.visibility = View.VISIBLE
            } else {
                binding.pbLoadingd.visibility = View.GONE
            }
        })
    }

    private fun refreshAdapter(items: List<String>) {
        val adapter = TVShortAdapter(this, items)
        binding.rvShorts.adapter = adapter
    }
}