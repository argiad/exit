package com.steegler.exit.network


interface NetworkService {
    suspend fun ratesRequest(completion: (ResultState) -> Unit)

    suspend fun getUpdatesFor(rateID: String, completion: (ResultState) -> Unit)
    companion object{
        fun getService() : NetworkService{
            return NetworkServiceImpl()
        }
    }
}