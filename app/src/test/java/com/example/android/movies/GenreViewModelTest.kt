package com.example.android.movies

import android.app.Application
import android.content.Context
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.android.movies.GenreTest.FakeGenreRepository
import com.example.android.movies.data.OperationResultList
import com.example.android.movies.models.Genre
import com.example.android.movies.viewmodels.GenreViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GenreViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var context: Application

    private lateinit var viewModel: GenreViewModel

    private lateinit var genreList: List<Genre>

    private lateinit var isViewLoadingObserver:Observer<Boolean>
    private lateinit var onMessageErrorObserver:Observer<Any>
    private lateinit var emptyListObserver:Observer<Boolean>
    private lateinit var onRenderGenresObserver:Observer<List<Genre>>

    private val fakeGenreRepository =
        FakeGenreRepository()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        `when`<Context>(context.applicationContext).thenReturn(context)
        Dispatchers.setMain(testDispatcher)

        setupObservers()
    }

    @ExperimentalCoroutinesApi
    @After
    fun cleanUp(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `retrieve genres, empty result`(){
        viewModel = GenreViewModel(fakeGenreRepository)

        with(viewModel){
            loadMovieGenres()
            isViewLoading.observeForever(isViewLoadingObserver)
            isEmptyList.observeForever(emptyListObserver)
            genreList.observeForever(onRenderGenresObserver)
        }

        runBlockingTest {
            val response = fakeGenreRepository.retrieveGenres()
            Assert.assertTrue(response is OperationResultList.Success)
            Assert.assertNotNull(viewModel.isViewLoading.value)
            Assert.assertTrue(viewModel.isEmptyList.value == true)
            Assert.assertTrue(viewModel.genreList.value?.size == 0)
        }
    }

    private fun setupObservers() {
        isViewLoadingObserver= mock(Observer::class.java) as Observer<Boolean>
        onMessageErrorObserver= mock(Observer::class.java) as Observer<Any>
        emptyListObserver= mock(Observer::class.java) as Observer<Boolean>
        onRenderGenresObserver= mock(Observer::class.java)as Observer<List<Genre>>
    }
}