package kleyton.com.br.topgames.repository

import kleyton.com.br.topgames.data.remote.RetrofitService
import kleyton.com.br.topgames.model.GameResponse

class GameListRepository(
    private val api: RetrofitService
): IGameListRepository {

    override suspend fun getGamesList(limit: Int): GameResponse {
        return api.getTopGames(limit = limit)
    }
}