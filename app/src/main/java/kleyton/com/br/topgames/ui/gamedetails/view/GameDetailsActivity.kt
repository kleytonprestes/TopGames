package kleyton.com.br.topgames.ui.gamedetails.view

import android.os.Bundle

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kleyton.com.br.topgames.CustomApplication
import kleyton.com.br.topgames.R
import kleyton.com.br.topgames.ui.gamedetails.viewmodel.GameDetailsViewModel
import kleyton.com.br.topgames.ui.gameslist.view.GamesListActivity.Companion.INTENT_EXTRA_GAME
import kleyton.com.br.topgames.model.Game
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.game_details.*

class GameDetailsActivity : AppCompatActivity() {

    private  var game: Game? = null

    private lateinit var model :GameDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_details)

        configToolbar()

        setupDetails()
    }

    private fun configToolbar() {
        model = GameDetailsViewModel()

        toolbar.title = resources.getString(R.string.details)
        setSupportActionBar(toolbar)
        model.configToolbar(supportActionBar)
    }

    private fun setupDetails() {
        game = intent.extras?.getParcelable(INTENT_EXTRA_GAME)

        game?.let {

            CustomApplication().loadImage(it.logo?.large, backgroundImage)
            game_details_name.text = it.name
            CustomApplication().loadImage(it.box?.large, image_box)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }
}