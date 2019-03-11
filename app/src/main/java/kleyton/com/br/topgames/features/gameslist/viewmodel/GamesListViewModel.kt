package kleyton.com.br.topgames.features.gameslist.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import kleyton.com.br.topgames.CustomApplication
import kleyton.com.br.topgames.features.gameslist.model.GamesListModel
import kleyton.com.br.topgames.features.gameslist.model.PaginationScrollListener
import kleyton.com.br.topgames.model.Game

class GamesListViewModel(customApplication: CustomApplication,
                         context: Context) {
    var gamesList = MutableLiveData<List<Game>>()

    var showError = MutableLiveData<String>()


    private var sizeList = 10
    private var isLastPage = false

    private var gamesListModel = GamesListModel(gamesList, customApplication.appDataBase,
        showError, context)

    fun getListValue(isNetworkAvailable: Boolean) {
        gamesListModel.getAllGames(isNetworkAvailable)
    }

    fun recyclerListener(recyclerView: RecyclerView, layoutManager: GridLayoutManager) {
        recyclerView.addOnScrollListener(object :PaginationScrollListener(layoutManager) {
            override fun loadMoreItems() {
                sizeList += CustomApplication.PAGE_SIZE
                gamesListModel.callDao(sizeList)

                if(sizeList >= 100) {
                    isLastPage = true
                }
            }

            override fun isLastPage(): Boolean {
                 return isLastPage
            }

        })
    }
}
