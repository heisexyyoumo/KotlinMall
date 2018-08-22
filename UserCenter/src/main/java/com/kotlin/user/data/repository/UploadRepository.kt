package com.kotlin.user.data.repository

import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.user.data.api.UploadApi
import com.kotlin.user.data.api.UserApi
import com.kotlin.user.data.protocol.*
import rx.Observable
import javax.inject.Inject

class UploadRepository @Inject constructor(){
    fun getUploadToken():Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java)
                .getUploadToken()
    }

}