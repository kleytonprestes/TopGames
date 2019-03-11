package kleyton.com.br.topgames.features.gameslist.view

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import kleyton.com.br.topgames.CustomApplication
import kleyton.com.br.topgames.R
import kleyton.com.br.topgames.features.gamedetails.view.GameDetailsActivity
import kleyton.com.br.topgames.features.gameslist.model.GameItemClickListener
import kleyton.com.br.topgames.features.gameslist.viewmodel.GamesListViewModel
import kleyton.com.br.topgames.model.Game
import kleyton.com.br.topgames.persistence.AppDataBase
import kotlinx.android.synthetic.main.activity_games_list.*


class GamesListActivity : AppCompatActivity(), GameItemClickListener, SwipeRefreshLayout.OnRefreshListener{

    private lateinit var gamesListModel: GamesListViewModel
    private lateinit var manager: GridLayoutManager
    private lateinit var adapter: GamesListAdapter

    private var application = CustomApplication()

    private var gamesList: ArrayList<Game>? = null

    companion object {
        const val SPAN_VAL = 2
        const val INTENT_EXTRA_GAME = "game_intent_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_list)

        application.appDataBase = AppDataBase.getDataBaseInstance(this)

        gamesListModel = GamesListViewModel(application, this)

        progress.visibility = VISIBLE

        requestGames()

        swipe_refresh.setOnRefreshListener(this)

        initObersvables()

    }

    fun setupRecycler() {

        manager = GridLayoutManager(this, SPAN_VAL)
        adapter = GamesListAdapter(gamesList, this, this)

        rv_games_list.layoutManager = manager
        rv_games_list.adapter = adapter
        rv_games_list.setHasFixedSize(true)

        gamesListModel.add(rv_games_list, manager)

        progress.visibility = GONE
    }

    fun initObersvables (){
        gamesListModel.gamesList.observe(this, Observer {
            it?.let { it ->
                gamesList = it as ArrayList<Game>?
                setupRecycler()
                swipe_refresh.isRefreshing = false
            }
        })

        gamesListModel.showError.observe(this, Observer {

            it?.let { message ->
                Snackbar
                    .make(swipe_refresh, message, Snackbar.LENGTH_LONG).show()
            }

        })
    }

    override fun onClick(game: Game?) {
        val newActivity = Intent(this, GameDetailsActivity::class.java)
        newActivity.putExtra(INTENT_EXTRA_GAME, game)
        startActivity(newActivity)
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun onRefresh() {
        requestGames()
    }

    fun requestGames() {

        gamesListModel.getListValue(isNetworkAvailable())
    }
}