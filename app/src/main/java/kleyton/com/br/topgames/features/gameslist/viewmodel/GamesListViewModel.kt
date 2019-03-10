package kleyton.com.br.topgames.features.gameslist.viewmodel

import android.arch.lifecycle.MutableLiveData
import kleyton.com.br.topgames.CustomApplication
import kleyton.com.br.topgames.features.gameslist.model.GamesListModel
import kleyton.com.br.topgames.model.Game
import kleyton.com.br.topgames.model.GameTop

class GamesListViewModel(customApplication: CustomApplication) {

    var gamesList = MutableLiveData<List<Game>>()
    private var gamesListModel = GamesListModel(gamesList, customApplication.appDataBase)

    fun getListValue(isNetworkAvailable: Boolean) {
        gamesListModel.getAllGames(isNetworkAvailable)
    }

    fun saveGames(gamesList: ArrayList<Game>?) {
        gamesListModel.insertGames(gamesList)
    }

}