package kleyton.com.br.topgames.features.gameslist.model

import kleyton.com.br.topgames.model.Game

interface GameItemClickListener {
    fun onClick(game: Game?)
}