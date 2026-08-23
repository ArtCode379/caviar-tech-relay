package caviarphoneukandni.computing.caviartechrelay.ui.composable.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import caviarphoneukandni.computing.caviartechrelay.R
import caviarphoneukandni.computing.caviartechrelay.ui.composable.shared.YBYAFContentWrapper
import caviarphoneukandni.computing.caviartechrelay.ui.composable.shared.YBYAFEmptyView
import caviarphoneukandni.computing.caviartechrelay.ui.state.CartItemUiState
import caviarphoneukandni.computing.caviartechrelay.ui.state.DataUiState
import caviarphoneukandni.computing.caviartechrelay.ui.viewmodel.CartViewModel
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val cartItemsState by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val totalPrice by viewModel.totalPrice.collectAsStateWithLifecycle()
    CartScreenContent(
        cartItemsState,
        modifier,
        totalPrice,
        viewModel::incrementProductInCart,
        viewModel::decrementItemInCart,
        viewModel::deleteFromCart,
        onNavigateToCheckoutScreen,
    )
}

@Composable
private fun CartScreenContent(
    cartItemsState: DataUiState<List<CartItemUiState>>,
    modifier: Modifier = Modifier,
    totalPrice: Double,
    onPlusItemClick: (Int) -> Unit,
    onMinusItemClick: (Int) -> Unit,
    onDeleteItem: (Int) -> Unit,
    onCompleteOrderButtonClick: () -> Unit,
) {
    YBYAFContentWrapper(
        dataState = cartItemsState,
        dataPopulated = {
            val data = (cartItemsState as DataUiState.Populated).data
            Column(modifier.fillMaxSize().padding(16.dp)) {
                LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(data, key = { it.productId }) { item ->
                        Card(shape = RoundedCornerShape(16.dp)) {
                            Row(Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                                AsyncImage(item.productImageUrl, item.productTitle, Modifier.size(72.dp), contentScale = ContentScale.Crop)
                                Column(Modifier.weight(1f).padding(horizontal = 10.dp)) {
                                    Text(item.productTitle, style = MaterialTheme.typography.titleMedium)
                                    Text("£%.2f".format(item.productPrice), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(onClick = { if (item.quantity == 1) onDeleteItem(item.productId) else onMinusItemClick(item.productId) }) {
                                        Icon(Icons.Default.Remove, stringResource(R.string.ybyaf_decrease_quantity_icon_description))
                                    }
                                    Text(item.quantity.toString())
                                    IconButton(onClick = { onPlusItemClick(item.productId) }) {
                                        Icon(Icons.Default.Add, stringResource(R.string.ybyaf_increase_quantity_icon_description))
                                    }
                                }
                            }
                        }
                    }
                }
                Row(Modifier.fillMaxWidth().padding(vertical = 14.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(stringResource(R.string.ybyaf_total), style = MaterialTheme.typography.titleLarge)
                    Text("£%.2f".format(totalPrice), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                }
                Button(onClick = onCompleteOrderButtonClick, modifier = Modifier.fillMaxWidth().height(52.dp)) {
                    Text(stringResource(R.string.ybyaf_proceed_checkout))
                }
            }
        },
        dataEmpty = {
            YBYAFEmptyView(
                modifier = Modifier.fillMaxSize(),
                primaryText = stringResource(R.string.ybyaf_cart_state_empty_primary_text),
            )
        },
    )
}
