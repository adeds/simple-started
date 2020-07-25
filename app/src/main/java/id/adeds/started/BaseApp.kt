package id.adeds.started

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import id.adeds.started.di.Injection
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApp : MultiDexApplication() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger(Level.DEBUG)
            androidContext(this@BaseApp)
            modules(Injection.baseModule)
        }
    }
}