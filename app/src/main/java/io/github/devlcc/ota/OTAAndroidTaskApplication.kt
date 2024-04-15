package io.github.devlcc.ota

import android.app.Application
import io.github.devlcc.core.data.di.getCoreDataKoinModule
import io.github.devlcc.ota.di.getViewModelsKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OTAAndroidTaskApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@OTAAndroidTaskApplication)
            modules(
                getCoreDataKoinModule(
                    isDebug = BuildConfig.DEBUG,
                ),
                getViewModelsKoinModule(),
                // TODO:: Include other Koin modules here...
            )
        }
    }

}