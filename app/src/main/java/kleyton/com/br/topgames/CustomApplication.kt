package kleyton.com.br.topgames

import android.app.Application
import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kleyton.com.br.topgames.persistence.AppDataBase

class CustomApplication : Application() {

    var appDataBase: AppDataBase? = null

    fun loadImage(context: Context, path: String?, imageView: ImageView) {

        if (path.isNullOrEmpty()) {

            return
        }

        Picasso.with(context)
            .load(path)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(imageView, object : Callback {
                override fun onSuccess() {

                }

                override fun onError() {
                    Picasso.with(context).load(path).networkPolicy(NetworkPolicy.NO_CACHE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .into(imageView)
                }
            })
    }

}