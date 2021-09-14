package com.teo.currency.exchanger.presentation.main.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.business.movies.model.MovieItem
import com.teo.currency.exchanger.components.providers.main.movies.MoviesComponentProvider
import com.teo.currency.exchanger.presentation.base.BaseFragment
import com.teo.currency.exchanger.presentation.main.movies.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_movies.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class MoviesFragment : BaseFragment(), MoviesView {

    @Inject
    lateinit var presenterProvider: Provider<MoviesPresenter>

    @ProvidePresenter
    fun providePresenter(): MoviesPresenter = presenterProvider.get()

    @InjectPresenter
    lateinit var presenter: MoviesPresenter

    private val adapter = MoviesAdapter()

    override fun initInjects() {
        MoviesComponentProvider.moviesComponent.inject(this)
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
        rvMovies.adapter = adapter
        adapter.movieClickListener = {
            presenter.onMovieClick(it)
        }
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
        showMessageDialog(getString(R.string.error_load)) {
            activity?.finish() //todo change logic to reload
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rvMovies.adapter = null
    }

}