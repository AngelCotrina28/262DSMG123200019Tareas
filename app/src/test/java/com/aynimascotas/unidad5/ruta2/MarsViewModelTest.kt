package com.aynimascotas.unidad5.ruta2

import com.aynimascotas.unidad5.ruta2.fake.FakeDataSource
import com.aynimascotas.unidad5.ruta2.fake.FakeNetworkMarsPhotosRepository
import com.aynimascotas.unidad5.ruta2.ui.MarsUiState
import com.aynimascotas.unidad5.ruta2.ui.MarsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

class MarsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest {
        val marsViewModel = MarsViewModel(
            marsPhotosRepository = FakeNetworkMarsPhotosRepository(),
        )
        assertEquals(
            MarsUiState.Success(FakeDataSource.photosList),
            marsViewModel.marsUiState,
        )
    }
}
