package com.tim.kotlinnative.shared.presentation

import com.tim.kotlinnative.shared.model.Member

interface DataRepository {
    var members: List<Member>?
    var onRefreshListeners: List<() -> Unit>

    suspend fun update()
}