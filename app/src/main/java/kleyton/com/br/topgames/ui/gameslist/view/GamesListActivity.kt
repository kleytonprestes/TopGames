package kleyton.com.br.topgames.ui.gameslist.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import kleyton.com.br.topgames.CustomApplication
import kleyton.com.br.topgames.R
import kleyton.com.br.topgames.ui.gamedetails.view.GameDetailsActivity
import kleyton.com.br.topgames.ui.gameslist.model.GameItemClickListener
import kleyton.com.br.topgames.ui.gameslist.viewmodel.GamesListViewModel
import kleyton.com.br.topgames.model.Game
import kleyton.com.br.topgames.persistence.AppDataBase
import kleyton.com.br.topgames.ui.gameslist.model.PaginationScrollListener
import kotlinx.android.synthetic.main.activity_games_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel



class GamesListActivity : AppCompatActivity(), GameItemClickListener, SwipeRefreshLayout.OnRefreshListener{

    private val viewmModel: GamesListViewModel by viewModel()
    private lateinit var manager: GridLayoutManager
    private lateinit var adapter: GamesListAdapter
    private var sizeList = 10
    private var isLastPage = false

    private var gamesList: ArrayList<Game>? = null

    companion object {
        const val SPAN_VAL = 2
        const val INTENT_EXTRA_GAME = "game_intent_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_list)

        progress.visibility = VISIBLE

        requestGames()

        swipeRefresh.setOnRefreshListener(this)

        initObservables()

    }

    private fun setupRecycler() {

        manager = GridLayoutManager(this, SPAN_VAL)
        adapter = GamesListAdapter(this, this)

        rvGamesList.layoutManager = manager
        rvGamesList.adapter = adapter
        rvGamesList.setHasFixedSize(true)

        recyclerListener(rvGamesList, manager)

        progress.visibility = GONE
    }

    private fun initObservables (){

        setupRecycler()

        viewmModel.gamesListLiveData.observe(this, {

            gamesList = it as ArrayList<Game>?
            swipeRefresh.isRefreshing = false
            adapter.addItems(gamesList)

        })

        viewmModel.showError.observe(this, Observer {

            Snackbar
                .make(swipeRefresh, it, Snackbar.LENGTH_LONG).show()

        })
    }

    override fun onClick(game: Game?) {
        val newActivity = Intent(this, GameDetailsActivity::class.java)
        newActivity.putExtra(INTENT_EXTRA_GAME, game)
        startActivity(newActivity)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }



    override fun onRefresh() {
        requestGames()
    }

    private fun requestGames() {

        viewmModel.getListValue(isNetworkAvailable())
    }

    private fun recyclerListener(recyclerView: RecyclerView, layoutManager: GridLayoutManager) {
        recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun loadMoreItems() {
                sizeList += CustomApplication.PAGE_SIZE

                viewmModel.callDao(sizeList)

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