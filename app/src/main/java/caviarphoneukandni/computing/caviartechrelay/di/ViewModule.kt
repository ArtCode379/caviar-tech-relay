package caviarphoneukandni.computing.caviartechrelay.di

import caviarphoneukandni.computing.caviartechrelay.ui.viewmodel.AppViewModel
import caviarphoneukandni.computing.caviartechrelay.ui.viewmodel.CartViewModel
import caviarphoneukandni.computing.caviartechrelay.ui.viewmodel.CheckoutViewModel
import caviarphoneukandni.computing.caviartechrelay.ui.viewmodel.YBYAFOnboardingVM
import caviarphoneukandni.computing.caviartechrelay.ui.viewmodel.OrderViewModel
import caviarphoneukandni.computing.caviartechrelay.ui.viewmodel.ProductDetailsViewModel
import caviarphoneukandni.computing.caviartechrelay.ui.viewmodel.ProductViewModel
import caviarphoneukandni.computing.caviartechrelay.ui.viewmodel.YBYAFSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        YBYAFSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        YBYAFOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}