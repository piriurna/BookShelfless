package com.piriurna.bookshelfless.presentation

import com.piriurna.bookshelfless.presentation.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}