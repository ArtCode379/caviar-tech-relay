package caviarphoneukandni.computing.caviartechrelay.di

import caviarphoneukandni.computing.caviartechrelay.data.repository.CartRepository
import caviarphoneukandni.computing.caviartechrelay.data.repository.YBYAFOnboardingRepo
import caviarphoneukandni.computing.caviartechrelay.data.repository.OrderRepository
import caviarphoneukandni.computing.caviartechrelay.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        YBYAFOnboardingRepo(
            ybyafOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}