package caviarphoneukandni.computing.caviartechrelay.ui.composable.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import caviarphoneukandni.computing.caviartechrelay.R
import caviarphoneukandni.computing.caviartechrelay.ui.theme.GradientEnd
import caviarphoneukandni.computing.caviartechrelay.ui.theme.GradientStart
import caviarphoneukandni.computing.caviartechrelay.ui.viewmodel.YBYAFSplashVM
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: YBYAFSplashVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    val onboardedState by viewModel.onboardedState.collectAsStateWithLifecycle()
    val animation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animation.animateTo(1f, tween(800))
        delay(700)
        if (onboardedState) {
            onNavigateToHomeScreen()
        } else {
            onNavigateToOnboarding()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(GradientStart, GradientEnd))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ybyaf_ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(144.dp)
                .alpha(animation.value)
                .scale(0.8f + animation.value * 0.2f),
        )
        Text(
            text = stringResource(R.string.ybyaf_app_name),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}
