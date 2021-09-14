package com.teo.currency.exchanger.presentation.main.movies.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teo.currency.exchanger.R
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.teo.currency.exchanger.business.movies.model.MovieItem
import com.teo.currency.exchanger.presentation.extensions.inflateView
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(
    parent: ViewGroup,
    private val movieClickListener: ((MovieItem) -> Unit)? = null
) :
    RecyclerView.ViewHolder(parent.inflateView(R.layout.item_movie)) {

    fun bind(movieItem: MovieItem) {
        with(itemView) {
            tvTitle.text = movieItem.title
            tvTitle.isVisible = !movieItem.title.isNullOrEmpty()

            tvIsAdult.isVisible = movieItem.adult

            tvDescription.text = movieItem.description
            tvDescription.isVisible = !movieItem.description.isNullOrEmpty()

            if (movieItem.posterPath.isNullOrEmpty()) {
                ivBanner.isVisible = false
            } else {
                ivBanner.isVisible = true
                Glide.with(itemView)
                    .load(movieItem.posterPath)
                    .into(ivBanner)
            }

            setOnClickListener { movieClickListener?.invoke(movieItem) }
        }
    }

}