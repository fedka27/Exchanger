package com.teo.currency.exchanger.presentation.main.movies.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.business.movies.model.MovieItem
import com.teo.currency.exchanger.components.providers.main.movies.details.MovieDetailsComponentProvider
import com.teo.currency.exchanger.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie_details.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class MovieDetailsFragment : BaseFragment(), MovieDetailsView {

    companion object {
        private const val KEY_MOVIE_ITEM = "KEY_MOVIE_ITEM"
        fun newInstance(movieItem: MovieItem): MovieDetailsFragment {
            return MovieDetailsFragment().apply {
                arguments = bundleOf(
                    KEY_MOVIE_ITEM to movieItem
                )
            }
        }
    }

    @Inject
    lateinit var presenterProvider: Provider<MovieDetailsPresenter>

    @ProvidePresenter
    fun providePresenter(): MovieDetailsPresenter = presenterProvider.get()

    @InjectPresenter
    lateinit var presenter: MovieDetailsPresenter

    override fun initInjects() {
        val movieDetails = requireArguments().getParcelable<MovieItem>(KEY_MOVIE_ITEM)!!

        MovieDetailsComponentProvider.get(movieDetails).inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initControls()
    }

    private fun initControls() {
        toolbar.navigationIcon = context?.getDrawable(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { presenter.onNavigationClick() }
    }

    override fun fillDetails(movieItem: MovieItem) {
        with(movieItem){
            toolbar.title = title
        }
        //TODO("Not yet implemented")
    }

}