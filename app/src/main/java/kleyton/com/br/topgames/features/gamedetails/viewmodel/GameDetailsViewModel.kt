package kleyton.com.br.topgames.features.gamedetails.viewmodel

import android.support.v7.app.ActionBar

class GameDetailsViewModel  {

    fun configToolbar(supportActionBar: ActionBar?) {

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}