package kleyton.com.br.topgames.data.remote

import kleyton.com.br.topgames.model.GameResponse

class MockApi: RetrofitService {

    override suspend fun getTopGames(
        accept: String,
        contentType: String,
        clientId: String,
        limit: Int
    ): GameResponse {
        return GameResponse(
            top = arrayListOf(),
            total = 10
        )
    }
}