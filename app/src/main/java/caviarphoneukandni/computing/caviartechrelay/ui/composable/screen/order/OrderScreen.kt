package caviarphoneukandni.computing.caviartechrelay.ui.composable.screen.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import caviarphoneukandni.computing.caviartechrelay.R
import caviarphoneukandni.computing.caviartechrelay.data.entity.OrderEntity
import caviarphoneukandni.computing.caviartechrelay.ui.composable.shared.YBYAFContentWrapper
import caviarphoneukandni.computing.caviartechrelay.ui.composable.shared.YBYAFEmptyView
import caviarphoneukandni.computing.caviartechrelay.ui.state.DataUiState
import caviarphoneukandni.computing.caviartechrelay.ui.theme.Success
import caviarphoneukandni.computing.caviartechrelay.ui.viewmodel.OrderViewModel
import java.time.format.DateTimeFormatter
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(modifier: Modifier = Modifier, viewModel: OrderViewModel = koinViewModel()) {
    val ordersState by viewModel.ordersState.collectAsState()
    YBYAFContentWrapper(
        dataState = ordersState,
        dataPopulated = {
            val orders = (ordersState as DataUiState.Populated).data.sortedByDescending { it.timestamp }
            LazyColumn(modifier.fillMaxSize(), contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(orders, key = { it.orderNumber }) { order ->
                    OrderCard(order)
                }
            }
        },
        dataEmpty = {
            YBYAFEmptyView(
                modifier = Modifier.fillMaxSize(),
                primaryText = stringResource(R.string.ybyaf_orders_state_empty_primary_text),
            )
        },
    )
}

@Composable
private fun OrderCard(order: OrderEntity) {
    Card(shape = RoundedCornerShape(16.dp)) {
        Column(Modifier.fillMaxWidth().padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Order #${order.orderNumber}", style = MaterialTheme.typography.titleMedium)
                Surface(color = Success.copy(alpha = 0.12f), shape = RoundedCornerShape(50)) {
                    Text("Reserved", color = Success, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                }
            }
            Text(order.timestamp.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm")), color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(order.description, Modifier.padding(vertical = 10.dp), style = MaterialTheme.typography.bodyMedium)
            Text("£%.2f".format(order.price), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text(stringResource(R.string.ybyaf_collect_24_hours), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
