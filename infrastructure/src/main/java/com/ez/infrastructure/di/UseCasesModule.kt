package com.ez.infrastructure.di

import com.ez.data.usecase.*
import com.ez.domain.usecase.*
import org.koin.dsl.module

val useCasesModule = module {

    factory<SaveGamesUseCase> { SaveGames(get()) }
    factory<SaveHeroesUseCase> { SaveHeroes(get()) }
    factory<SaveSearchUsersUseCase> { SaveSearchUsers(get()) }
    factory<GetRecentSearchUsersUseCase> { GetRecentSearchUsers(get()) }
    factory<GetGamesDataSourceFactoryUseCase> { GetGamesDataSourceFactory(get()) }
    factory<GetHeroesDataSourceFactoryUseCase> { GetHeroesDataSourceFactory(get()) }
    factory<GetMatchesUseCase> { GetMatches(get()) }
    factory<GetGameDetailUseCase> { GetGameDetail(get()) }
    factory<GetHeroesUseCase> { GetHeroes(get()) }
    factory<GetUsersByNameUseCase> { GetUsersByName(get()) }
    factory<GetTopPlayersUseCase> { GetTopPlayers(get()) }
}