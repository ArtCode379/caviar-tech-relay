package caviarphoneukandni.computing.caviartechrelay.di

import caviarphoneukandni.computing.caviartechrelay.data.datastore.YBYAFOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { YBYAFOnboardingPrefs(androidContext()) }
}