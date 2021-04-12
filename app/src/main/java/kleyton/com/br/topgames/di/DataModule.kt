package kleyton.com.br.topgames.di

import androidx.room.Room
import kleyton.com.br.topgames.persistence.AppDataBase
import kleyton.com.br.topgames.persistence.AppDataBase.Companion.APP_DATABASE_NAME
import kleyton.com.br.topgames.repository.GameListRepository
import kleyton.com.br.topgames.repository.IGameListRepository
import org.koin.dsl.module


val dataModule = module {

    single {
        Room.databaseBuilder(
            get(),
            AppDataBase::class.java,
            APP_DATABASE_NAME)
            .build()
    }

    single {
        get<AppDataBase>().gameDao
    }

    single<IGameListRepository> {
        GameListRepository(
            api = get()
        )
    }
}