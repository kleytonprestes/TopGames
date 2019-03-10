package kleyton.com.br.topgames.features.gameslist.view

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import kleyton.com.br.topgames.CustomApplication
import kleyton.com.br.topgames.R
import kleyton.com.br.topgames.features.gamedetails.view.GameDetailsActivity
import kleyton.com.br.topgames.features.gameslist.model.GameItemClickListener
import kleyton.com.br.topgames.features.gameslist.viewmodel.GamesListViewModel
import kleyton.com.br.topgames.model.Game
import kleyton.com.br.topgames.persistence.AppDataBase
import kotlinx.android.synthetic.main.activity_games_list.*

class GamesListActivity : AppCompatActivity(), GameItemClickListener {

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

        gamesListModel = GamesListViewModel(application)

        gamesListModel.getListValue(isNetworkAvailable())

        initObersvables()

    }

    fun setupRecycler() {

        manager = GridLayoutManager(this, SPAN_VAL)
        adapter = GamesListAdapter(gamesList, this, this)

        rv_games_list.layoutManager = manager
        rv_games_list.adapter = adapter
        rv_games_list.setHasFixedSize(true)
    }

    fun initObersvables (){
        gamesListModel.gamesList.observe(this, Observer {
            it.let { it ->
                gamesList = it as ArrayList<Game>?
                gamesListModel.saveGames(gamesList)
                setupRecycler()
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
}