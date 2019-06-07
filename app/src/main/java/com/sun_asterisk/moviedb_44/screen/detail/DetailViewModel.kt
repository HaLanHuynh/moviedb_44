package com.sun_asterisk.moviedb_44.screen.detail

import androidx.databinding.ObservableField
import com.sun_asterisk.moviedb_44.data.model.Movie
import com.sun_asterisk.moviedb_44.data.repository.MovieRepository
import com.sun_asterisk.moviedb_44.screen.base.BaseViewModel
import com.sun_asterisk.moviedb_44.utils.Constant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel constructor(private val movieRepository: MovieRepository) : BaseViewModel() {

    val movieObservable: ObservableField<Movie> = ObservableField()
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun onStart() {
        initData()
    }

    private fun initData() {
        mCompositeDisposable.add(
            movieRepository.getMovieLatest()
                .take(1)
                .subscribe({ movie -> movieObservable.set(movie) },
                    { throwable -> throwable.localizedMessage }))
    }

    override fun onStop() {
        mCompositeDisposable.clear()
    }
}
