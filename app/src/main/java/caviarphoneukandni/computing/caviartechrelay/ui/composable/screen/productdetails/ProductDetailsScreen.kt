package caviarphoneukandni.computing.caviartechrelay.ui.composable.screen.productdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import caviarphoneukandni.computing.caviartechrelay.R
import caviarphoneukandni.computing.caviartechrelay.data.model.Product
import caviarphoneukandni.computing.caviartechrelay.ui.composable.shared.YBYAFContentWrapper
import caviarphoneukandni.computing.caviartechrelay.ui.composable.shared.YBYAFEmptyView
import caviarphoneukandni.computing.caviartechrelay.ui.state.DataUiState
import caviarphoneukandni.computing.caviartechrelay.ui.viewmodel.ProductDetailsViewModel
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val productState by viewModel.productDetailsState.collectAsState()
    LaunchedEffect(productId) { viewModel.observeProductDetails(productId) }
    ProductDetailsScreenContent(productState, modifier, viewModel::addProductToCart)
}

@Composable
private fun ProductDetailsScreenContent(
    productState: DataUiState<Product>,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit,
) {
    var cartAdded by remember { mutableStateOf(false) }
    LaunchedEffect(cartAdded) {
        if (cartAdded) {
            delay(2000)
            cartAdded = false
        }
    }
    Box(modifier.fillMaxSize()) {
        YBYAFContentWrapper(
            dataState = productState,
            dataPopulated = {
                val product = (productState as DataUiState.Populated).data
                Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                    AsyncImage(
                        model = product.imageUrl,
                        contentDescription = product.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(300.dp),
                    )
                    Column(Modifier.padding(20.dp)) {
                        Text(product.title, style = MaterialTheme.typography.headlineMedium)
                        Text("£%.2f".format(product.price), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                        Card(shape = RoundedCornerShape(50)) {
                            Text(stringResource(product.category.titleRes), Modifier.padding(horizontal = 12.dp, vertical = 6.dp))
                        }
                        Text(product.description, Modifier.padding(top = 18.dp), style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Button(
                            onClick = {
                                onAddToCart()
                                cartAdded = true
                            },
                            modifier = Modifier.fillMaxWidth().padding(top = 28.dp),
                        ) {
                            Text(stringResource(R.string.ybyaf_button_add_to_cart_label))
                        }
                    }
                }
            },
            dataEmpty = {
                YBYAFEmptyView(
                    modifier = Modifier.fillMaxSize(),
                    primaryText = stringResource(R.string.ybyaf_product_details_state_empty_primary_text),
                )
            },
        )
        AnimatedVisibility(
            visible = cartAdded,
            enter = slideInVertically { it },
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            Text(
                text = "✓ Added to cart",
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
            )
        }
    }
}
