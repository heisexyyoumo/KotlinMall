package com.kotlin.base.data.protocol


//为了请求完成后对返回的数据进行统一处理.
class BaseResp<T>(val status: Int, val message: String, val data: T)