package com.example.android.movies.GenreTest

import com.example.android.movies.bl.IMoviesGenresRetrieve
import com.example.android.movies.data.OperationResultList
import com.example.android.movies.models.Genre

class FakeGenreRepository: IMoviesGenresRetrieve {

    private var mockGenreList: MutableList<Genre> = mutableListOf()

    init {
        mockEmptyData()
        //mockData()
    }

    override suspend fun retrieveGenres(): OperationResultList<Genre> {
        return OperationResultList.Success(mockGenreList)
    }

    private fun mockData() {
        mockGenreList.add(0, Genre(1, "Action"))
        mockGenreList.add(1, Genre(2, "Comedy"))
        mockGenreList.add(2, Genre(3, "Drama"))

    }

    private fun mockEmptyData(){
        mockGenreList = mutableListOf()
    }
}