package kleyton.com.br.topgames.repository

import kleyton.com.br.topgames.model.GameResponse

interface IGameListRepository {
    suspend fun getGamesList(limit: Int) : GameResponse
}