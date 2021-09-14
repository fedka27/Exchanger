package com.teo.currency.exchanger.presentation.main.movies.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.teo.currency.exchanger.business.exchanger.model.CurrencyExchange
import com.teo.currency.exchanger.business.movies.model.MovieItem

class MoviesAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    private val list = arrayListOf<MovieItem>()

    var movieClickListener: ((MovieItem) -> Unit)? = null

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent) {
            movieClickListener?.invoke(it)
        }
    }

    override fun onBindViewHolder(holderMovie: MovieViewHolder, position: Int) {
        holderMovie.bind(list[position])
    }

    fun setMovies(values: List<MovieItem>) {
        val diffCallback = MoviesDiffCallback(list, values)

        this.list.clear()
        this.list.addAll(values)

        val result = DiffUtil.calculateDiff(diffCallback)
        result.dispatchUpdatesTo(this)
    }

    fun updateItem(movieItem: MovieItem) {
        val index = list.indexOf(movieItem)
        if (index != -1) {
            list[index] = movieItem
            notifyItemChanged(index)
        }
    }

    class MoviesDiffCallback(
        private val oldList: List<MovieItem>,
        private val newList: List<MovieItem>

    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = oldList[oldItemPosition]
            val new = newList[newItemPosition]
            return old.id == new.id &&
                    old.isFavorite == new.isFavorite
        }

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

    }
}