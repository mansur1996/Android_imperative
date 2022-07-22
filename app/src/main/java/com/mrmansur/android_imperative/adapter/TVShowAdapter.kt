package com.mrmansur.android_imperative.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mrmansur.android_imperative.R
import com.mrmansur.android_imperative.activity.MainActivity
import com.mrmansur.android_imperative.databinding.ItemTvShowBinding
import com.mrmansur.android_imperative.model.TVShow

class TVShowAdapter(var activity: MainActivity, var items: ArrayList<TVShow>) : BaseAdapter() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false)
        return TVShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tvShow: TVShow = items[position]

        if (holder is TVShowViewHolder) {
            Glide.with(activity).load(tvShow.image_thumbnail_path).into(holder.binding.ivMovie)
            holder.binding.tvName.text = tvShow.name
            holder.binding.tvType.text = tvShow.network

            ViewCompat.setTransitionName(holder.binding.ivMovie, tvShow.name)
            holder.binding.ivMovie.setOnClickListener {
                //Save TVShow to Room
                activity.viewModel.insertTVShowToDB(tvShow)
                // Call Details Activity
                activity.callDetailsActivity(tvShow, holder.binding.ivMovie)
            }




        }
    }


    inner class TVShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemTvShowBinding.bind(view)
    }

    fun setNewTVShows(tvShows: ArrayList<TVShow>){
//        items.clear()
        items.addAll(tvShows)
        notifyDataSetChanged()
    }
}