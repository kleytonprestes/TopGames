package kleyton.com.br.topgames.di

import kleyton.com.br.topgames.ui.gameslist.viewmodel.GamesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        GamesListViewModel(
            repository = get(),
            gameDao = get()
        )
    }
}