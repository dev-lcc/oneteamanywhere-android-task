package io.github.devlcc.ota.di

import io.github.devlcc.ota.ui.presentation.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

fun getViewModelsKoinModule(): Module = module {
    viewModelOf(::HomeScreenViewModel)
}