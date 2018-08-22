package com.kotlin.user.service

import com.bumptech.glide.load.model.stream.StreamStringLoader
import com.kotlin.user.data.protocol.UserInfo
import rx.Observable


interface UserService {

    fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean>

    fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo>

    fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean>

    fun resetPwd(mobile: String, pwd: String): Observable<Boolean>

    fun editUser(userIcon: String, userName: String, userGender: String,
                 userSign: String): Observable<UserInfo>
}