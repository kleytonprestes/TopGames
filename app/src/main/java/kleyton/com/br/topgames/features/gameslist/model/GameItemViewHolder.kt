package kleyton.com.br.topgames.features.gameslist.model

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kleyton.com.br.topgames.R

class GameItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var gameName: TextView = itemView.findViewById(R.id.game_name)
    var gameTemplate: ImageView = itemView.findViewById(R.id.game_image)

}