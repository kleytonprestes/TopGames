package kleyton.com.br.topgames.persistence

import android.arch.persistence.room.*
import kleyton.com.br.topgames.model.Game


@Dao
interface GameDao {


    @Query("SELECT * FROM game LIMIT :limitGames")
    fun getAllGames(limitGames: Int): List<Game>

    @get:Query("SELECT * FROM GAME")
    val getgames : List<Game>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertGame(games: Game)

    @Delete
    fun deleteGame(game: Game)

}