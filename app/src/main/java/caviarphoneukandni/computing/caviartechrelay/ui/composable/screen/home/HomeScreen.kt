package caviarphoneukandni.computing.caviartechrelay.ui.composable.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import caviarphoneukandni.computing.caviartechrelay.R
import caviarphoneukandni.computing.caviartechrelay.data.model.Product
import caviarphoneukandni.computing.caviartechrelay.data.model.ProductCategory
import caviarphoneukandni.computing.caviartechrelay.ui.composable.shared.YBYAFContentWrapper
import caviarphoneukandni.computing.caviartechrelay.ui.composable.shared.YBYAFEmptyView
import caviarphoneukandni.computing.caviartechrelay.ui.state.DataUiState
import caviarphoneukandni.computing.caviartechrelay.ui.viewmodel.ProductViewModel
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val productsState by viewModel.productsState.collectAsState()
    HomeContent(productsState, modifier, onNavigateToProductDetails, viewModel::addToCart)
}

@Composable
private fun HomeContent(
    productsState: DataUiState<List<Product>>,
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: (productId: Int) -> Unit,
    onAddProductToCart: (productId: Int) -> Unit,
) {
    YBYAFContentWrapper(
        dataState = productsState,
        dataPopulated = {
            val products = (productsState as DataUiState.Populated).data
            var selectedCategory by remember { mutableStateOf<ProductCategory?>(null) }
            val filtered = products.filter { selectedCategory == null || it.category == selectedCategory }
            Column(modifier = modifier.fillMaxSize()) {
                FeaturedPager(products.take(4), onNavigateToProductDetails)
                Text(
                    text = stringResource(R.string.ybyaf_shop_categories),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 16.dp, top = 18.dp),
                )
                LazyRow(
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    item {
                        AssistChip(onClick = { selectedCategory = null }, label = { Text(stringResource(R.string.ybyaf_category_all)) })
                    }
                    items(ProductCategory.entries.size) { index ->
                        val category = ProductCategory.entries[index]
                        AssistChip(onClick = { selectedCategory = category }, label = { Text(stringResource(category.titleRes)) })
                    }
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.weight(1f),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(filtered, key = { it.id }) { product ->
                        ProductCard(product, onNavigateToProductDetails, onAddProductToCart)
                    }
                }
            }
        },
        dataEmpty = {
            YBYAFEmptyView(
                modifier = Modifier.fillMaxSize(),
                primaryText = stringResource(R.string.ybyaf_products_state_empty_primary_text),
            )
        },
    )
}

@Composable
private fun FeaturedPager(products: List<Product>, onOpen: (Int) -> Unit) {
    val pagerState = rememberPagerState(pageCount = { products.size })
    LaunchedEffect(products.size) {
        while (products.isNotEmpty()) {
            delay(4000)
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % products.size)
        }
    }
    Column {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) { page ->
            val product = products[page]
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .height(190.dp)
                    .clickable { onOpen(product.id) },
                shape = RoundedCornerShape(16.dp),
            ) {
                Box {
                    AsyncImage(product.imageUrl, null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        Text(product.title, color = androidx.compose.ui.graphics.Color.White, style = MaterialTheme.typography.titleLarge)
                        Text("£%.2f".format(product.price), color = androidx.compose.ui.graphics.Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
        Row(Modifier.align(Alignment.CenterHorizontally), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            repeat(products.size) { index ->
                Spacer(
                    Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(if (index == pagerState.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline)
                )
            }
        }
    }
}

@Composable
private fun ProductCard(product: Product, onOpen: (Int) -> Unit, onAdd: (Int) -> Unit) {
    Card(modifier = Modifier.clickable { onOpen(product.id) }) {
        Column {
            AsyncImage(product.imageUrl, product.title, Modifier.fillMaxWidth().height(112.dp), contentScale = ContentScale.Crop)
            Column(Modifier.padding(10.dp)) {
                Text(product.title, style = MaterialTheme.typography.titleMedium, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Text(stringResource(product.category.titleRes), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text("£%.2f".format(product.price), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    FilledTonalIconButton(onClick = { onAdd(product.id) }, modifier = Modifier.size(36.dp)) {
                        Icon(Icons.Default.AddShoppingCart, stringResource(R.string.ybyaf_button_add_to_cart_label), modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
    }
}
