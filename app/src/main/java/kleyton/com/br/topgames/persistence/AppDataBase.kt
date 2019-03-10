package kleyton.com.br.topgames.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import kleyton.com.br.topgames.model.Game


@Database(entities = [Game::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {

        private const val DB_NAME = "games_db"

        private val INSTANCE: AppDataBase? = null
        fun getDataBaseInstance(context: Context): AppDataBase {
            return INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                DB_NAME
            ).build()
        }
    }
}
