package com.steegler.exit.domain

import com.steegler.exit.ResourceHelper
import com.steegler.exit.domain.repository.RatesRepo
import com.steegler.exit.network.NetworkService
import org.junit.Before
import org.junit.jupiter.api.Test


class BusinessLogicTest(private val repo: RatesRepo, private val networkService: NetworkService) {

    private lateinit var businessLogic: BusinessLogic

    @Before
    fun setUp(){
        businessLogic = BusinessLogic.build(repo = repo, networkService = networkService)
    }

    @Test
    fun updateRates() {
        assert( 2 == 2 )
    }

    @Test
    fun getUpdatesFor() {
    }

    @Test
    fun getRateBy() {
    }
}