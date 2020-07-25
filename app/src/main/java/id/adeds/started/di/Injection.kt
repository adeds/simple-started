package id.adeds.started.di

import androidx.preference.PreferenceManager
import id.adeds.started.data.local.SharedPrefs
import id.adeds.started.data.network.MyService
import id.adeds.started.data.network.RetrofitBuilder
import id.adeds.started.data.repository.MyRepository
import id.adeds.started.view.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Injection {

    private fun provideMyRepo(
        retrofitBuilder: RetrofitBuilder,
        prefs: SharedPrefs
    ): MyRepository {
        val service = retrofitBuilder.build().create(MyService::class.java)
        return MyRepository(service, prefs)
    }

    val baseModule = module {
        single { PreferenceManager.getDefaultSharedPreferences(androidApplication()) }
        single { PreferenceManager.getDefaultSharedPreferences(androidApplication()).edit() }

        single { SharedPrefs(get(), get()) }

        single { RetrofitBuilder() }
        single { provideMyRepo(get(), get()) }

        viewModel { MainViewModel(get()) }
    }
}