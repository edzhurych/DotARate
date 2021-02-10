package com.ez.dotarate.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.koin.core.component.KoinComponent


@Suppress("EXPERIMENTAL_API_USAGE")
abstract class BaseViewModel(
    application: Application
) : AndroidViewModel(application), KoinComponent