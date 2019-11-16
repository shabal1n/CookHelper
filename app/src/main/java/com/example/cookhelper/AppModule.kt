package com.example.cookhelper

import android.content.Context
import com.example.cookhelper.base.CoroutineProvider
import com.example.cookhelper.navigation.recipes.RecipesViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    factory {
        CoroutineProvider()
    }
    single {
        FirebaseDatabase.getInstance().reference
    }

    single{
        FirebaseAuth.getInstance()
    }

    single {
        FirebaseStorage.getInstance().reference
    }
    factory {
        FileUploadRepository(get())
    }

    single {
        androidContext().getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
    }

    viewModel {
        RegistrationViewModel(get())
    }

    viewModel {
        RecipesViewModel()
    }
}
