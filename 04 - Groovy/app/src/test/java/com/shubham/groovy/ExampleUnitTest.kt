package com.shubham.groovy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.mock
import petros.efthymiou.groovy.utils.getValueForTest

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlaylistViewModelShould {

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule() // This will allow the execution of LiveData to happen
    // instantly, so we can use the values in our test.

    val viewModel: PlaylistViewModel
    val repository: PlaylistRepository = mock()

    init {
        viewModel = PlaylistViewModel() // it is only object that is going to be a real object & everything
        // around it must be mocked.
    }

    @Test
    fun getPlaylistsFromRepository() {

        viewModel.playlists.getValueForTest()
        // getValueForTest is from utils/LiveDataTestExtensions.kt, this is forcing the LiveData to complete all the
        // operations it needs in order to get and emit the values.

        verify(repository, times(1)).getPlaylists()
    }
}