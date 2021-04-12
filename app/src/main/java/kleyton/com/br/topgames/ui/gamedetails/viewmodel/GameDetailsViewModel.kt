package kleyton.com.br.topgames.ui.gamedetails.viewmodel

import androidx.appcompat.app.ActionBar

class GameDetailsViewModel  {

    fun configToolbar(supportActionBar: ActionBar?) {

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}