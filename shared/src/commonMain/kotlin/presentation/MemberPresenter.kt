package com.tim.kotlinnative.shared.presentation

import com.tim.kotlinnative.shared.ApplicationDispatcher
import kotlinx.coroutines.launch

class MemberPresenter(
    private val view: MembersView,
    private val repository: DataRepository
) : CoroutinePresenter(ApplicationDispatcher, view) {

    override fun onCreate() {
        view.isUpdating = isFirstDataLoading()
        repository.onRefreshListeners += onRefreshListener
        showData()
        updateData()
    }

    override fun onDestroy() {
        super.onDestroy()
        repository.onRefreshListeners -= onRefreshListener
    }


    private val onRefreshListener: () -> Unit = this::showData

    private fun showData() {
        view.onUpdate(repository.members.orEmpty())
    }

    private fun updateData() {
        launch {
            repository.update()
            showData()
        }.invokeOnCompletion {
            view.isUpdating = false
        }
    }

    private fun isFirstDataLoading() = repository.members == null
}