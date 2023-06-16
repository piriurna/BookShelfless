package com.piriurna.bookshelfless.presentation.di

import android.content.Context
import com.piriurna.bookshelfless.presentation.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    AndroidSupportInjectionModule::class,
    DatabaseModule::class,
    NetworkModule::class,
    ViewModelModule::class,
    FragmentBindingModule::class
])
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}