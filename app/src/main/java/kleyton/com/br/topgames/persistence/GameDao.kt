package kleyton.com.br.topgames.persistence

import android.arch.persistence.room.*
import kleyton.com.br.topgames.model.Game

@Dao
interface GameDao {

    @get:Query("SELECT * FROM game")
    val getAllGames: List<Game>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertGame(games: Game)

    @Delete
    fun deleteGame(game: Game)

}