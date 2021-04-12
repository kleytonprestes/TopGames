package kleyton.com.br.topgames.persistence


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kleyton.com.br.topgames.model.Game


@Database(entities = [Game::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract val gameDao: GameDao

    companion object {

        const val APP_DATABASE_NAME = "app_database"

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {

            synchronized(this) {
                var instance: AppDataBase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,
                        APP_DATABASE_NAME
                    ).build()
                }

                return instance
            }
        }
    }
}
