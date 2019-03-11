package kleyton.com.br.topgames.api

import kleyton.com.br.topgames.model.GameResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService  {

    @GET("top")
    fun getTopGames(
        @Header("Accept") accept: String = "application/vnd.twitch.v3+json",
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Client-ID") clientId: String = "sow2rr3d7ml56c4f8re85xzggo8635",
        @Query("limit") limit : Int) : Call<GameResponse>


}