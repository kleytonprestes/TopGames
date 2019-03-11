package kleyton.com.br.topgames.features.gameslist.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kleyton.com.br.topgames.CustomApplication
import kleyton.com.br.topgames.R
import kleyton.com.br.topgames.features.gameslist.model.GameItemClickListener
import kleyton.com.br.topgames.features.gameslist.model.GameItemViewHolder
import kleyton.com.br.topgames.model.Game

class GamesListAdapter(
    val context: Context,
    private val listener: GameItemClickListener) : RecyclerView.Adapter<GameItemViewHolder>() {

    private var topGamesList = ArrayList<Game>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_game, parent, false)

        return GameItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topGamesList.size
    }

    override fun onBindViewHolder(holder: GameItemViewHolder, position: Int) {
        val gameItem = topGamesList.get(position)

        holder.gameName.text = gameItem.name

        CustomApplication().loadImage(
            context, gameItem.logo?.large, holder.gameTemplate)

        holder.itemView.setOnClickListener{
            listener.onClick(gameItem)
        }
    }

    fun addItems(items: ArrayList<Game>?) {

        items?.let { topGamesList = it }
        notifyDataSetChanged()
    }
}