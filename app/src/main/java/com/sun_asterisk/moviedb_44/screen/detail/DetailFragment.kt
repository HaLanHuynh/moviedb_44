package com.sun_asterisk.moviedb_44.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun_asterisk.moviedb_44.R
import com.sun_asterisk.moviedb_44.data.repository.MovieRepository
import com.sun_asterisk.moviedb_44.data.source.local.MovieLocalDataSource
import com.sun_asterisk.moviedb_44.data.source.remote.MovieRemoteDataSource
import com.sun_asterisk.moviedb_44.databinding.FragmentDetailMovieBinding
import com.sun_asterisk.moviedb_44.screen.base.BaseFragment

class DetailFragment : BaseFragment() {
    lateinit var binding: FragmentDetailMovieBinding
    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_movie, container, false)
        viewModel = DetailViewModel(
            MovieRepository.getInstance(
                MovieLocalDataSource(),
                MovieRemoteDataSource.getInstance()
            )
        )

        binding.viewModel = viewModel
        viewModel.onStart()
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}
