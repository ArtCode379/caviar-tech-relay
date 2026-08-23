package caviarphoneukandni.computing.caviartechrelay.data.model

import androidx.annotation.StringRes
import caviarphoneukandni.computing.caviartechrelay.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    COMPUTERS(R.string.ybyaf_category_computers),
    COMPONENTS(R.string.ybyaf_category_components),
    PERIPHERALS(R.string.ybyaf_category_peripherals),
    SOFTWARE(R.string.ybyaf_category_software),
}
