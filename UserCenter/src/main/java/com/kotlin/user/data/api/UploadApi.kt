package com.kotlin.user.data.api

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.user.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UploadApi {
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>

}