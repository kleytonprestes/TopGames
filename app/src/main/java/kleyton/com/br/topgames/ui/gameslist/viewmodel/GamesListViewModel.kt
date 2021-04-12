package kleyton.com.br.topgames.ui.gameslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kleyton.com.br.topgames.CustomApplication
import kleyton.com.br.topgames.R
import kleyton.com.br.topgames.data.remote.SafeResponse
import kleyton.com.br.topgames.data.remote.safeRequest
import kleyton.com.br.topgames.model.Game
import kleyton.com.br.topgames.persistence.GameDao
import kleyton.com.br.topgames.repository.IGameListRepository
import kotlinx.coroutines.launch
import timber.log.Timber


class GamesListViewModel(
    private val repository: IGameListRepository,
    private val gameDao: GameDao
): ViewModel() {

    private var _showError = MutableLiveData<Int>()
    val showError: LiveData<Int>
        get() = _showError

    private var _gamesListLiveData = MutableLiveData<List<Game>>()
    val gamesListLiveData: LiveData<List<Game>>
        get() = _gamesListLiveData

    private var gameslist: ArrayList<Game> = arrayListOf()

    fun getListValue(isNetworkAvailable: Boolean) {
        if (isNetworkAvailable) {
            callApi()
        } else {

            _showError.value = R.string.error_connection

            callDao(CustomApplication.PAGE_SIZE)


        }
    }

    fun insertGames(gamesList: ArrayList<Game>?) {
        viewModelScope.launch {

            gamesList?.forEach { it ->
                it.let { game ->
                    gameDao.insertGame(game)
                }
            }

            callDao(CustomApplication.PAGE_SIZE)
        }
    }

    private fun callApi() {

        viewModelScope.launch {

            when (val request = safeRequest {
                repository.getGamesList(100)
            }) {
                is SafeResponse.Success -> {

                    request.value.top.forEach { game ->
                        gameslist.add(game.game)
                    }

                    insertGames(gameslist)
                }

                is SafeResponse.GenericError -> {
                    Timber.d(request.error?.message())
                }

                is SafeResponse.NetworkError -> {

                }
            }


        }

    }

     fun callDao(limit: Int) {

        viewModelScope.launch {
            val gameArrayList = gameDao.getAllGames(limit)

            _gamesListLiveData.value = gameArrayList
        }
    }


}
