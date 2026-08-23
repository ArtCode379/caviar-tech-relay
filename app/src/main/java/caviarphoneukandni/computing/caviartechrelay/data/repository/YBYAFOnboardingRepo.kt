package caviarphoneukandni.computing.caviartechrelay.data.repository

import caviarphoneukandni.computing.caviartechrelay.data.datastore.YBYAFOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class YBYAFOnboardingRepo(
    private val ybyafOnboardingStoreManager: YBYAFOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return ybyafOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            ybyafOnboardingStoreManager.setOnboardedState(state)
        }
    }
}