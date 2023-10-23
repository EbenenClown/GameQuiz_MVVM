package com.tommygr.gamequiz.data.repositories

import com.tommygr.gamequiz.data.source.local.daos.StatisticDao
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteStatisticDataSource
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

class StatisticRepositoryImplTest {
    @RelaxedMockK
    private lateinit var mockLocalDataSource: StatisticDao
    @RelaxedMockK
    private lateinit var mockRemoteDataSource: RemoteStatisticDataSource
    private lateinit var statisticRepositoryImpl: StatisticRepositoryImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        statisticRepositoryImpl = StatisticRepositoryImpl(mockLocalDataSource, mockRemoteDataSource)
    }



    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }
}