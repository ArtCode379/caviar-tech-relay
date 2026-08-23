package caviarphoneukandni.computing.caviartechrelay.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import caviarphoneukandni.computing.caviartechrelay.data.dao.CartItemDao
import caviarphoneukandni.computing.caviartechrelay.data.dao.OrderDao
import caviarphoneukandni.computing.caviartechrelay.data.database.converter.Converters
import caviarphoneukandni.computing.caviartechrelay.data.entity.CartItemEntity
import caviarphoneukandni.computing.caviartechrelay.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class YBYAFDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}