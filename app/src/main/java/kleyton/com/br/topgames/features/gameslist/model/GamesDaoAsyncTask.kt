package kleyton.com.br.topgames.features.gameslist.model

import kleyton.com.br.topgames.model.Game
import java.util.*

interface GamesDaoAsyncTaskc {

    fun getList(gameArrayList: ArrayList<Game>)

}