package kleyton.com.br.topgames.ui.gameslist.model


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kleyton.com.br.topgames.R

class GameItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var gameName: TextView = itemView.findViewById(R.id.game_name)
    var gameTemplate: ImageView = itemView.findViewById(R.id.game_image)

}