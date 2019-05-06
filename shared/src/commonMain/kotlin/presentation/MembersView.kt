package com.tim.kotlinnative.shared.presentation

import com.tim.kotlinnative.shared.model.Member

interface MembersView : BaseView {
    var isUpdating: Boolean
    fun onUpdate(members: List<Member>)
}