package com.teo.currency.exchanger.presentation.main.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.business.movies.model.MovieItem
import com.teo.currency.exchanger.components.providers.main.movies.MoviesComponentProvider
import com.teo.currency.exchanger.presentation.base.BaseFragment
import com.teo.currency.exchanger.presentation.main.movies.adapter.MoviesAdapter
import com.teo.currency.exchanger.presentation.main.movies.details.MovieDetailsFragment
import kotlinx.android.synthetic.main.fragment_movies.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class MoviesFragment : BaseFragment(), MoviesView {

    companion object {
        private const val KEY_SHOW_FAVORITES = "KEY_SHOW_FAVORITES"
        fun newInstance(showFavorites: Boolean): MoviesFragment {
            return MoviesFragment().apply {
                arguments = bundleOf(
                    KEY_SHOW_FAVORITES to showFavorites
                )
            }
        }
    }

    @Inject
    lateinit var presenterProvider: Provider<MoviesPresenter>

    @ProvidePresenter
    fun providePresenter(): MoviesPresenter = presenterProvider.get()

    @InjectPresenter
    lateinit var presenter: MoviesPresenter

    private val adapter = MoviesAdapter()

    override fun initInjects() {
        val showFavorites = arguments?.getBoolean(KEY_SHOW_FAVORITES) ?: false

        MoviesComponentProvider.get(showFavorites).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initControls()
    }

    private fun initControls() {
        toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.favoritesAction -> {
                    presenter.favoritesClick()
                    true
                }
                else -> false
            }
        }

        rvMovies.adapter = adapter
        adapter.movieClickListener = {
            presenter.onMovieClick(it)
        }
    }

    override fun setVisibleNavigationButton(visible: Boolean) {
        toolbar.navigationIcon =
            if (visible) context?.getDrawable(R.drawable.ic_arrow_back)
            else null

        toolbar.setNavigationOnClickListener { presenter.onNavigationClick() }
    }

    override fun setVisibleFavoritesButton(visible: Boolean) {
        toolbar.menu.findItem(R.id.favoritesAction).isVisible = visible
    }

    override fun showProgress() {
        progressBar.visibility = VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = GONE
    }

    override fun displayContent() {
        rvMovies.isVisible = true
    }

    override fun hideContent() {
        rvMovies.isVisible = false
    }

    override fun setMovies(movies: List<MovieItem>) {
        adapter.setMovies(movies)
    }

    override fun showErrorLoad() {
        hideProgress()
        tvFailedLoad.isVisible = true
        tvFailedLoad.text = String.format(
            "%s\n%s",
            getString(R.string.movies_error_load),
            getString(R.string.info_movie_favorites)
        )
    }

    override fun openFavoritesScreen() {
        fragmentManager?.apply {
            beginTransaction()
                .add(id, MoviesFragment.newInstance(true))
                .addToBackStack(null)
                .commit()
        }
    }

    override fun openMovieDetailsScreen(movieItem: MovieItem) {
        fragmentManager?.apply {
            beginTransaction()
                .add(id, MovieDetailsFragment.newInstance(movieItem))
                .addToBackStack(null)
                .commit()
        }    }

    override fun onDestroyView() {
        super.onDestroyView()
        rvMovies.adapter = null
    }
}