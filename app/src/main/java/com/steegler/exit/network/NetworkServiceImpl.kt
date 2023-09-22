package com.steegler.exit.network

import com.steegler.exit.data.remote.Rate
import com.steegler.exit.data.remote.RateUpdateResponse
import com.steegler.exit.data.remote.RatesResponse
import kotlinx.serialization.json.Json
import java.net.URL

class NetworkServiceImpl : NetworkService {

    companion object {
        const val BASE_URL = "https://api.coincap.io/v2/"
    }

    override suspend fun ratesRequest(completion: (ResultState) -> Unit) {
        val jsonString: String = try {
            URL("${BASE_URL}rates").readText()
        } catch (e: Exception) {
            completion(ResultState(error = "Something went wrong"))
            return
        }
        val decodedData: List<Rate> = try {
            Json.decodeFromString<RatesResponse>(jsonString).rates
        } catch (e: Exception) {
            completion(ResultState(error = e.localizedMessage))
            return
        }
        completion(ResultState(itemsList = decodedData))
    }

    override suspend fun getUpdatesFor(rateID: String, completion: (ResultState) -> Unit) {
        val jsonString: String = try {
            URL("${BASE_URL}rates/$rateID").readText()
        } catch (e: Exception) {
            completion(ResultState(error = "Something went wrong"))
            return
        }
        val decodedData: List<Rate> = try {
            listOf(Json.decodeFromString<RateUpdateResponse>(jsonString).rate)
        } catch (e: Exception) {
            completion(ResultState(error = e.localizedMessage))
            return
        }
        completion(ResultState(itemsList = decodedData))
    }
}