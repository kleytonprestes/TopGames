package kleyton.com.br.topgames

import android.app.Application
import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kleyton.com.br.topgames.di.dataModule
import kleyton.com.br.topgames.di.retrofitModule
import kleyton.com.br.topgames.di.viewModelModule
import kleyton.com.br.topgames.persistence.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.lang.Exception

class CustomApplication : Application() {

    companion object {

        const val PAGE_SIZE = 10
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CustomApplication)
            modules(
                dataModule,
                viewModelModule,
                retrofitModule
            )
        }
    }

    var appDataBase: AppDataBase? = null

    fun loadImage(path: String?, imageView: ImageView) {

        if (path.isNullOrEmpty()) {

            return
        }

        Picasso.get()
            .load(path)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(imageView, object : Callback {

                override fun onSuccess() {

                }

                override fun onError(e: Exception) {
                    Picasso.get().load(path).networkPolicy(NetworkPolicy.NO_CACHE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .into(imageView)
                }
            })
    }

}