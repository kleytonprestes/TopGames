package kleyton.com.br.topgames.features.gamedetails.viewmodel

import android.support.v7.app.ActionBar

class GameDetailsViewModel  {

    fun configToolbar(supportActionBar: ActionBar?) {

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

}