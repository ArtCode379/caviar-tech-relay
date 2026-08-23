package caviarphoneukandni.computing.caviartechrelay.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import caviarphoneukandni.computing.caviartechrelay.R

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val supportUrl = stringResource(R.string.ybyaf_customer_support_link)
    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text(stringResource(R.string.ybyaf_about), style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(10.dp))
        Card {
            Column(Modifier.fillMaxWidth().padding(16.dp)) {
                Text(stringResource(R.string.ybyaf_app_name), style = MaterialTheme.typography.titleMedium)
                Text("${stringResource(R.string.ybyaf_settings_screen_company_label)}: ${stringResource(R.string.ybyaf_company_name)}")
                Text("${stringResource(R.string.ybyaf_settings_screen_version_label)}: ${stringResource(R.string.ybyaf_app_version)}")
            }
        }
        Spacer(Modifier.height(24.dp))
        Text(stringResource(R.string.ybyaf_support), style = MaterialTheme.typography.titleLarge)
        Text(stringResource(R.string.ybyaf_support_description), Modifier.padding(vertical = 10.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
        Button(
            onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(supportUrl))) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(Icons.Default.Language, null)
            Text(stringResource(R.string.ybyaf_settings_screen_customer_support_label), Modifier.padding(start = 8.dp))
        }
    }
}
