package kleyton.com.br.topgames.features.gameslist.model

import android.arch.lifecycle.MutableLiveData
import android.os.AsyncTask
import android.util.Log
import kleyton.com.br.topgames.api.RetrofitInitializer
import kleyton.com.br.topgames.model.Game
import kleyton.com.br.topgames.model.GameResponse
import kleyton.com.br.topgames.model.GameTop
import kleyton.com.br.topgames.persistence.AppDataBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GamesListModel(var gamesListLiveData: MutableLiveData<List<Game>>,
                     var appDataBase: AppDataBase?) : GamesDaoAsyncTaskc{


    fun callApi() {
        val call = RetrofitInitializer().retrofitService().getTopGames(limit = 10)
        val gameList: ArrayList<Game> = ArrayList()

        call.enqueue(object: Callback<GameResponse> {
            override fun onResponse(call: Call<GameResponse>,
                                    response: Response<GameResponse>) {
                response.body()?.let {
                    it.top?.forEach {
                        it.game?.let { game -> gameList.add(game) }
                    }
                    gamesListLiveData.value = gameList
                }
            }

            override fun onFailure(call: Call<GameResponse>, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })

    }

    fun callDao() {
        GamesAsyncTask(appDataBase, this).execute()
    }

    fun getAllGames(isNetworkAvailable: Boolean){
        if (isNetworkAvailable) {
            callApi()
        } else {
            callDao()
        }
    }

    override fun getList(gameArrayList: ArrayList<Game>) {

        gamesListLiveData.value = gameArrayList
    }

    fun insertGames(gamesList: ArrayList<Game>?) {
        doAsync {
            gamesList?.forEach { it ->
                it.let { game ->
                    appDataBase?.gameDao()?.insertGame(game)
                }
            }
        }.execute()
    }

    class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            handler()
            return null
        }
    }

}