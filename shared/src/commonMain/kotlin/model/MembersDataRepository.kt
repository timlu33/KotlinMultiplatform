package com.tim.kotlinnative.shared.model

import com.tim.kotlinnative.shared.api.GitHubApi
import com.tim.kotlinnative.shared.api.UpdateProblem
import com.tim.kotlinnative.shared.presentation.DataRepository

class MembersDataRepository(private val api: GitHubApi): DataRepository {

    override var members: List<Member>? = null
    override var onRefreshListeners: List<() -> Unit> = emptyList()

    override suspend fun update() {
        val newMembers = try {
            api.getMembers()
        } catch (e: Throwable) {
            throw UpdateProblem()
        }


        if (newMembers != members) {
            members = newMembers
            callRefreshListeners()
        }
    }

    private fun callRefreshListeners() {
        onRefreshListeners.forEach { it() }
    }
}