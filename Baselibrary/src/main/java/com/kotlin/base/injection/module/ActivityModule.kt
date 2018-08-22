package com.kotlin.base.injection.module

import android.app.Activity
import android.support.v4.app.FragmentActivity
import com.kotlin.base.injection.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun providesActivity():Activity{
        return this.activity
    }
}