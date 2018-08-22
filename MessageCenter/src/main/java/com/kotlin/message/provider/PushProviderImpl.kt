package com.kotlin.message.provider

import android.content.Context
import cn.jpush.android.api.JPushInterface
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.provider.PushProvider
import com.kotlin.provider.router.RouterPath


@Route(path = RouterPath.MessageCenter.PATH_MESSAGE_PUSH)
class PushProviderImpl : PushProvider {

    private var mContext: Context? = null

    override fun getPushId(): String {
        return JPushInterface.getRegistrationID(mContext)
    }

    override fun init(context: Context?) {
        mContext = context
    }
}