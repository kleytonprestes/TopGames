package kleyton.com.br.topgames.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    companion object {
        const val BASE_URL = "https://api.twitch.tv/kraken/games/"

    }

    private var retrofit = init()

    fun init() =
       Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()


    fun retrofitService() = retrofit.create(RetrofitService::class.java)
}