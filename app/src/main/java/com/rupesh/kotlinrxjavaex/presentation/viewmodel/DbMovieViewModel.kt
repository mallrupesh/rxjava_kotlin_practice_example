package com.rupesh.kotlinrxjavaex.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rupesh.kotlinrxjavaex.data.db.entity.DbMovie
import com.rupesh.kotlinrxjavaex.domain.repository.DbMovieRepository
import com.rupesh.kotlinrxjavaex.domain.usecase.DeleteSavedMovie
import com.rupesh.kotlinrxjavaex.domain.usecase.GetAllSavedMovies
import com.rupesh.kotlinrxjavaex.domain.usecase.SaveMovieToDb
import com.rupesh.kotlinrxjavaex.domain.util.Event

/**
 * DbMovieViewModel is [androidx.lifecycle.ViewModel]
 * Forwards database operations to Repository
 * [com.rupesh.kotlinrxjavaex.repository.DbMovieRepository]
 * and subsequently provides LiveData to Views to observe.
 * Instantiates DbMoviesViewModel
 * @param repository the DbMovieRepository
 * @author Rupesh Mall
 * @since 1.0
 */
class DbMovieViewModel(
    val getAllSavedMovies: GetAllSavedMovies,
    val saveMovieToDb: SaveMovieToDb,
    val deleteSavedMovie: DeleteSavedMovie
) : ViewModel()  {

    /**
     * Livedata of type DbMovie to be observed by [com.rupesh.kotlinrxjavaex.view.WatchListFragment]
     */
    var dbMovieMutableLiveData: MutableLiveData<List<DbMovie>> = MutableLiveData()

    val statusMessage = MutableLiveData<Event<String>>()

    /**
     * Gets a list of DMovie wrapped inside MutableLiveData
     * @return the LiveData<List<DMovie>
     */
    fun getAllMovieFromDb() {
        dbMovieMutableLiveData = getAllSavedMovies.execute()
        statusMessage.value = Event("Your saved movies")
    }

    /**
     * Forwards the operation to add DbMovie to DbMovieRepository
     * @param id the DMovie id
     * @param title the DMovie title
     * @param rating the DMovie rating
     * @param overview the DMovie overview
     * @param releaseDate the DMovie release date
     * @param posterPath the DMovie poster path (url)
     */
    fun addMovieToDB(id: Long, title: String,
                           rating: Double, overview: String,
                           releaseDate: String, posterPath: String ) {


        saveMovieToDb.execute(id, title, rating, overview, releaseDate, posterPath)
        statusMessage.value = Event("Saved movie $title")
    }

    /**
     * Forwards the operation to delete DbMovie to DbMovieRepository
     * @param dbMovie the DbMovie instance to be deleted
     */
    fun deleteMovieFromDB(dbMovie: DbMovie) {
        deleteSavedMovie.execute(dbMovie)
        statusMessage.value = Event("Deleted movie ${dbMovie.title}")
    }

    /**
     * Clears the connection between Observables and Observers
     * added in CompositeDisposables
     */
    fun clear() {
        getAllSavedMovies.clear()
        super.onCleared()
    }
}