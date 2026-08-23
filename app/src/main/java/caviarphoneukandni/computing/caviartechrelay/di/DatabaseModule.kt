package caviarphoneukandni.computing.caviartechrelay.di

import androidx.room.Room
import caviarphoneukandni.computing.caviartechrelay.data.database.YBYAFDatabase
import org.koin.dsl.module

private const val DB_NAME = "ybyaf_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = YBYAFDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<YBYAFDatabase>().cartItemDao() }

    single { get<YBYAFDatabase>().orderDao() }
}