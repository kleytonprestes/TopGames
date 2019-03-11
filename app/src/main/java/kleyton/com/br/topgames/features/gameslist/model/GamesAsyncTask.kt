package kleyton.com.br.topgames.features.gameslist.model

import android.os.AsyncTask
import kleyton.com.br.topgames.model.Game
import kleyton.com.br.topgames.persistence.AppDataBase
import java.util.*

class GamesAsyncTask(internal var appDataBase: AppDataBase?,
                     var gamesDaoAsyncTaskc: GamesDaoAsyncTaskc,
                     var limit: Int) :
    AsyncTask<Void, Void, ArrayList<Game>>() {

    override fun doInBackground(vararg voids: Void): ArrayList<Game> {

        return appDataBase?.gameDao()?.getAllGames(limit) as ArrayList<Game>
    }

    override fun onPostExecute(propertyArrayList: ArrayList<Game>) {
        super.onPostExecute(propertyArrayList)
        gamesDaoAsyncTaskc.getList(propertyArrayList)

    }
}
