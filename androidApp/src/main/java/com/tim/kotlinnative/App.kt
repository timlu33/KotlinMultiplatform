package com.tim.kotlinnative

import android.app.Application
import com.tim.kotlinnative.shared.api.GitHubApi
import com.tim.kotlinnative.shared.model.MembersDataRepository
import com.tim.kotlinnative.shared.presentation.DataRepository

class App : Application() {

    val dataRepository: DataRepository by lazy {
        MembersDataRepository(GitHubApi())
    }

}