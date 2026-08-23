package caviarphoneukandni.computing.caviartechrelay.ui.composable.screen.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import caviarphoneukandni.computing.caviartechrelay.R
import caviarphoneukandni.computing.caviartechrelay.data.entity.OrderEntity
import caviarphoneukandni.computing.caviartechrelay.ui.composable.shared.YBYAFContentWrapper
import caviarphoneukandni.computing.caviartechrelay.ui.composable.shared.YBYAFEmptyView
import caviarphoneukandni.computing.caviartechrelay.ui.state.DataUiState
import caviarphoneukandni.computing.caviartechrelay.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        YBYAFContentWrapper(
            dataState = ordersState,

            dataPopulated = {
                val data = (ordersState as DataUiState.Populated).data

            },

            dataEmpty = {
                YBYAFEmptyView(
                    primaryText = stringResource(R.string.ybyaf_orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}