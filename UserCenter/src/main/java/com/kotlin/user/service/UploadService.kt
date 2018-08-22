package com.kotlin.user.service

import rx.Observable


interface UploadService {

    fun getUploadToken(): Observable<String>

}