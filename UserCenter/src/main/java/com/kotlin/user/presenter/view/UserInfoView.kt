package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.user.data.protocol.UserInfo

open interface UserInfoView : BaseView {

    fun onGetUploadTokenResult(result: String)

    fun onEditUserResult(result: UserInfo)

}