package com.steegler.exit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.steegler.exit.data.entity.RateEntity
import com.steegler.exit.domain.BusinessLogic
import com.steegler.exit.domain.repository.RatesRepo
import com.steegler.exit.network.NetworkService
import com.steegler.exit.network.ResultState
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    private lateinit var repo: RatesRepo
    private lateinit var networkService: NetworkService

    private lateinit var businessLogic: BusinessLogic

    @Before
    fun setUpMocks() {
        repo = object : RatesRepo {
            override fun getAllItemsStream(): LiveData<List<RateEntity>> {
                return MutableLiveData(ResourceHelper.listOfRateEntities)
            }

            override fun getItemStream(id: String): RateEntity? {
                return ResourceHelper.getEntityItem(id)
            }

            override fun insertAll(vararg messages: RateEntity) {
//                RateEntity("a","b","c","d","e")
            }

        }

        networkService = object : NetworkService {
            override suspend fun ratesRequest(completion: (ResultState) -> Unit) {
                completion(ResultState(itemsList = ResourceHelper.listOfRates))
            }

            override suspend fun getUpdatesFor(rateID: String, completion: (ResultState) -> Unit) {
                completion(ResultState(itemsList = listOf(ResourceHelper.correctRateItem!!)))
            }

        }
    }


    @Test
    fun mocks_isCorrect() {
        assertEquals(182, repo.getAllItemsStream().value?.size)
        assertEquals("bitcoin-cash", repo.getItemStream("bitcoin-cash")?.id)
    }


    @Test
    fun businessLogic(){
        businessLogic = BusinessLogic.build(repo, networkService)
        runBlocking {
            val rateById = businessLogic.getRateBy("bitcoin-cash")
            assertEquals("bitcoin-cash", rateById?.id)
        }
    }


}

