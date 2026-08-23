package caviarphoneukandni.computing.caviartechrelay.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import caviarphoneukandni.computing.caviartechrelay.data.model.Product
import caviarphoneukandni.computing.caviartechrelay.data.repository.CartRepository
import caviarphoneukandni.computing.caviartechrelay.data.repository.ProductRepository
import caviarphoneukandni.computing.caviartechrelay.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
) : ViewModel() {
    private val _productsState = MutableStateFlow<DataUiState<List<Product>>>(DataUiState.Initial)
    val productsState: StateFlow<DataUiState<List<Product>>>
        get() = _productsState.asStateFlow()

    init {
        observeProducts()
    }

    private fun observeProducts() {
        viewModelScope.launch {
            productRepository.observeAll().collect { products ->
                _productsState.update { DataUiState.from(products) }
            }
        }
    }

    fun addToCart(productId: Int) {
        viewModelScope.launch {
            val products = _productsState.value
            if (products is DataUiState.Populated) {
                val product = products.data.find { it.id == productId } ?: return@launch
                cartRepository.incrementProductQuantityOrAdd(product)
            }
        }
    }
}