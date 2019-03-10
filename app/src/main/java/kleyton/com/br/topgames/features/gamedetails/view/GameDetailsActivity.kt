package kleyton.com.br.topgames.features.gamedetails.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kleyton.com.br.topgames.CustomApplication
import kleyton.com.br.topgames.R
import kleyton.com.br.topgames.features.gameslist.view.GamesListActivity.Companion.INTENT_EXTRA_GAME
import kleyton.com.br.topgames.model.Game
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.game_details.*

class GameDetailsActivity : AppCompatActivity() {

    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_details)

        configToolbar()

        setupDetails()
    }

    fun setupDetails() {
        game = intent.extras.getParcelable(INTENT_EXTRA_GAME)

        CustomApplication().loadImage(this, game.logo?.large, background_image)
        game_details_name.text = game.name
        CustomApplication().loadImage(this, game.box?.large, image_box)
    }

    private fun configToolbar() {

        toolbar.title = resources.getString(R.string.details)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }
}